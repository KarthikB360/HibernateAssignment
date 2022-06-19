package com.blockbuster.repository;

import java.util.List;

import com.blockbuster.entity.Customer;
import com.blockbuster.entity.Movie;

public interface CustomCustomerRepository extends GenericRepository{

	public List<Movie> findMoviesByCustomerID(int customerId);

	public List<Customer> findExpiredDueDateCustomers();

	public List<Customer> findBorrowedCustomers();

	public List<Customer> findExpiredDueDateOfCustomersByCurrentDate();

}
