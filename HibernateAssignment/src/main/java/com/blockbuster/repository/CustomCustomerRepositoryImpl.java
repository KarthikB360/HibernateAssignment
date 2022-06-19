package com.blockbuster.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.blockbuster.entity.Customer;
import com.blockbuster.entity.Movie;

public class CustomCustomerRepositoryImpl extends GenericRepositoryImpl implements CustomCustomerRepository {

	@Override
	public List<Movie> findMoviesByCustomerID(int customerId) {
		return entityManagerFactory.createEntityManager()
				.createQuery("select m from Movie m where m.customer.id = :customerId", Movie.class)
				.setParameter("customerId", customerId).getResultList();
	}

	@Override
	public List<Customer> findBorrowedCustomers() {
		return entityManagerFactory.createEntityManager()
				.createQuery("select c from Customer c where c.borrowed =:value", Customer.class)
				.setParameter("value", true).getResultList();
	}

	@Override
	public List<Customer> findExpiredDueDateCustomers() {
		return entityManagerFactory.createEntityManager()
				.createQuery("select c from Customer c where c.expired =:value", Customer.class)
				.setParameter("value", true).getResultList();
	}

	@Override
	public List<Customer> findExpiredDueDateOfCustomersByCurrentDate() {
		return entityManagerFactory.createEntityManager()
				.createQuery("select c from Customer c where c.dueDate <:value", Customer.class)
				.setParameter("value", LocalDateTime.now()).getResultList();
	}
}
