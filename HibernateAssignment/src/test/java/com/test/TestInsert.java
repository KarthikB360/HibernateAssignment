package com.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.blockbuster.entity.Address;
import com.blockbuster.entity.Blockbuster;
import com.blockbuster.entity.Customer;
import com.blockbuster.entity.Employee;
import com.blockbuster.entity.Employee.EmploymentType;
import com.blockbuster.entity.Employee.ShiftType;
import com.blockbuster.entity.LoanTypeDetail;
import com.blockbuster.entity.LoanTypeDetail.LoanType;
import com.blockbuster.entity.Movie;
import com.blockbuster.entity.Shop;
import com.blockbuster.repository.GenericRepository;
import com.blockbuster.repository.GenericRepositoryImpl;

class TestInsert {

	GenericRepository genericRepository;

	@BeforeEach
	public void before() {
		genericRepository = new GenericRepositoryImpl();
	}

	@Test
	public void testAddBlockbuster() {
		Blockbuster blockbuster = new Blockbuster();
		genericRepository.save(blockbuster);
	}

	@Test
	public void testAddShop() {
		Blockbuster blockbuster = genericRepository.findById(Blockbuster.class, 5);
		Shop shop = new Shop("Mumbai", blockbuster);
		genericRepository.save(shop);
	}

	@Test
	public void testAddEmployee() {
		Shop shop = genericRepository.findById(Shop.class, 3);

		Employee employee1 = new Employee("xyz_manager", LocalDate.of(2000, 10, 5), EmploymentType.MANAGER,
				ShiftType.FULL_SHIFT, shop);
		Employee employee2 = new Employee("pqr_staff_a", LocalDate.of(2001, 11, 6), EmploymentType.COUNTER_STAFF,
				ShiftType.MORNING_SHIFT, shop);
		Employee employee3 = new Employee("zxc_staff_b", LocalDate.of(2002, 12, 7), EmploymentType.COUNTER_STAFF,
				ShiftType.EVENING_SHIFT, shop);

		genericRepository.save(employee1);
		genericRepository.save(employee2);
		genericRepository.save(employee3);
	}

	@Test
	public void testAddMovie() {
		Shop shop = genericRepository.findById(Shop.class, 3);
		Movie movie1 = new Movie("Movie_1", true, shop, 0);
		Movie movie2 = new Movie("Movie_2", true, shop, 15);
		Movie movie3 = new Movie("Movie_3", true, shop, 18);
		genericRepository.saveBatch(List.of(movie1, movie2, movie3));
	}

	@Test
	public void testAddLoanTypeDetail() {
		LoanTypeDetail detail1 = new LoanTypeDetail(LoanType.BASIC, 10, 1);
		LoanTypeDetail detail2 = new LoanTypeDetail(LoanType.STANDARD, 100, 7);
		LoanTypeDetail detail3 = new LoanTypeDetail(LoanType.PREMIUM, 1000, 30);
		genericRepository.saveBatch(List.of(detail1, detail2, detail3));
	}

	@Test
	public void testAddCustomer() {
		LoanTypeDetail detail = genericRepository.findById(LoanTypeDetail.class, 2);
		Customer customer = new Customer("Cust_xyz", LocalDate.of(2000, 5, 6), detail);
		genericRepository.save(customer);
	}

	@Test
	public void testAddAddress() {
		Customer customer = genericRepository.findById(Customer.class, 1);
		Address address = new Address("Street_1", "City_1", "State_1", 123456, customer);
		genericRepository.save(address);
	}

}
