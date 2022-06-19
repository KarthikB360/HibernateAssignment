package com.blockbuster.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.blockbuster.entity.Employee;
import com.blockbuster.exception.CustomeException;
import com.blockbuster.repository.CustomShopRepository;
import com.blockbuster.repository.CustomShopRepositoryImpl;

public class ShopService {

	public Employee assignShopManager(Employee employee) {
		CustomShopRepository shopRepository = new CustomShopRepositoryImpl();
		Optional<Employee> optionalEmployee = shopRepository.findEmployeeByEmploymentTypeAndShopID(employee);

		if (optionalEmployee.isPresent()) {
			throw new CustomeException("Manager already exist for the shop");
		} else {
			employee = (Employee) shopRepository.save(employee);
		}
		return employee;
	}

	public Employee assignCouterStaff(Employee employee) {
		CustomShopRepository shopRepository = new CustomShopRepositoryImpl();
		Optional<Employee> optionalEmployee = shopRepository.findEmployeeByEmploymentTypeStaffAndShopID(employee);

		if (optionalEmployee.isPresent()) {
			throw new CustomeException("Counter staff already exist for the specified shift at the shop");
		} else {
			employee = (Employee) shopRepository.save(employee);
		}
		return employee;
	}

	@Transactional
	public List<Employee> assignAllCouterStaffs(List<Employee> employees) {
		for (Employee employee : employees) {
			employee = assignCouterStaff(employee);
		}
		return employees;
	}

}
