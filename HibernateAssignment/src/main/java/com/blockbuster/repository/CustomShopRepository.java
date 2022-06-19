package com.blockbuster.repository;

import java.util.List;
import java.util.Optional;

import com.blockbuster.entity.Employee;
import com.blockbuster.entity.Movie;

public interface CustomShopRepository extends GenericRepository{

	public Optional<Employee> findEmployeeByEmploymentTypeAndShopID(Employee employee);

	public Optional<Employee> findEmployeeByEmploymentTypeStaffAndShopID(Employee employee);

	public List<Movie> findMoviesByShopID(int shopId);

	public List<Movie> findAvailableMoviesByShopID(int shopId);

	public List<Employee> findCounterStaffByShopID(int shopId);

}
