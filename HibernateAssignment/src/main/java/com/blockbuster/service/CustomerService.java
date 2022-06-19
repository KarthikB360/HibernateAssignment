package com.blockbuster.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.blockbuster.entity.Customer;
import com.blockbuster.entity.CustomerTransaction;
import com.blockbuster.entity.Movie;
import com.blockbuster.exception.CustomeException;
import com.blockbuster.repository.CustomCustomerRepository;
import com.blockbuster.repository.CustomCustomerRepositoryImpl;

public class CustomerService {

	public void issueMoviesToCustomer(Customer customer, List<Movie> movies, double totalAmount) {
		int age = Period.between(customer.getDob(), LocalDate.now()).getYears();

		if (!issueByCertificateCheck(age, movies)) {
			throw new CustomeException("Movie cannot be issued, reason is under age for this certification movies");
		}

		List<Object> transactions = new ArrayList<>();
		double billAmount = 0;
		for (Movie movie : movies) {
			billAmount += customer.getLoanType().getLoanAmount();
			movie.setInStock(false);
			movie.setCustomer(customer);
			CustomerTransaction transaction = new CustomerTransaction(movie, LocalDateTime.now(),
					customer.getLoanType().getLoanAmount(), "Collect");
			transactions.add(transaction);
		}
		if (totalAmount != billAmount) {
			throw new CustomeException("Bill amount is: " + billAmount);
		}

		LocalDateTime dateTime = LocalDateTime.now();
		dateTime = dateTime.plusDays(customer.getLoanType().getLoanPeriod());

		customer.setBorrowed(true);
		customer.setExpired(false);
		customer.setDueDate(dateTime);

		CustomCustomerRepository customerRepository = new CustomCustomerRepositoryImpl();
		customerRepository.saveBatch(transactions);
		customerRepository.save(customer);
		for (Object object : movies) {
			customerRepository.save(object);
		}
	}

	public void receiveMoviesFromCustomer(Customer customer, List<Movie> movies, double dueAmount) {
		CustomCustomerRepository customerRepository = new CustomCustomerRepositoryImpl();
		List<Movie> dbMovies = customerRepository.findMoviesByCustomerID(customer.getId());

		updateCustomerExpiredFieldByDuedate();
		customer = customerRepository.findById(Customer.class, customer.getId());

		List<Integer> dbMovieIds = dbMovies.stream().map(m -> m.getId()).collect(Collectors.toList());
		List<Integer> movieIds = movies.stream().map(m -> m.getId()).collect(Collectors.toList());
		if (!movieIds.containsAll(dbMovieIds)) {
			throw new CustomeException("Few movies are missing, please return all movies at once");
		}

		double calculatedDueAmount = 0;
		if (customer.isExpired()) {
			long gapOfDays = ChronoUnit.DAYS.between(customer.getDueDate(), LocalDateTime.now());
			calculatedDueAmount = 10 * movies.size() * gapOfDays; // Per day 10rs fine for each movie.

			if (calculatedDueAmount != dueAmount) {
				throw new CustomeException("Due amount is: " + calculatedDueAmount);
			}
		}

		List<Object> transactions = new ArrayList<>();
		double calculatedDueAmountPerMovie = calculatedDueAmount / movies.size();
		for (Movie movie : movies) {
			movie.setInStock(true);
			movie.setCustomer(null);
			CustomerTransaction transaction = new CustomerTransaction(movie, LocalDateTime.now(),
					calculatedDueAmountPerMovie, "Deposit");
			transactions.add(transaction);
		}
		customer.setBorrowed(false);
		customer.setExpired(false);
		customer.setDueDate(null);

		customerRepository.saveBatch(transactions);
		customerRepository.save(customer);
		for (Object object : movies) {
			customerRepository.save(object);
		}
	}

	public boolean issueByCertificateCheck(int age, List<Movie> movies) {
		boolean flag = true;

		for (Movie movie : movies) {
			if (movie.getCertificate() > age) {
				flag = false;
			}
		}
		return flag;
	}

	public void updateCustomerExpiredFieldByDuedate() {
		CustomCustomerRepository customerRepository = new CustomCustomerRepositoryImpl();
		List<Customer> customers = customerRepository.findBorrowedCustomers();

		customers = customers.stream().filter(c -> c.getDueDate().isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
		customers.stream().forEach(c -> c.setExpired(true));

		if (customers.size() > 0) {
			for (Customer customer : customers) {
				customerRepository.save(customer);
			}
		}
	}

}
