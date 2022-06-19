package com.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.blockbuster.entity.Blockbuster;
import com.blockbuster.entity.Customer;
import com.blockbuster.entity.Employee;
import com.blockbuster.entity.Employee.EmploymentType;
import com.blockbuster.entity.Employee.ShiftType;
import com.blockbuster.entity.LoanTypeDetail;
import com.blockbuster.entity.Movie;
import com.blockbuster.entity.Shop;
import com.blockbuster.repository.CustomCustomerRepository;
import com.blockbuster.repository.CustomCustomerRepositoryImpl;
import com.blockbuster.repository.CustomShopRepository;
import com.blockbuster.repository.CustomShopRepositoryImpl;
import com.blockbuster.repository.GenericRepository;
import com.blockbuster.repository.GenericRepositoryImpl;
import com.blockbuster.service.CustomerService;
import com.blockbuster.service.ShopService;

class AssignmentTestCases {

	ShopService shopService = new ShopService();
	CustomerService customerService = new CustomerService();

	GenericRepository genericRepository;
	CustomShopRepository shopRepository = new CustomShopRepositoryImpl();
	CustomCustomerRepository customerRepository = new CustomCustomerRepositoryImpl();

	@BeforeEach
	public void before() {
		genericRepository = new GenericRepositoryImpl();
	}

	@Test
	public void testAddShop() {
		Blockbuster blockbuster = genericRepository.findById(Blockbuster.class, 1);
		Shop shop = new Shop("Mumbai", blockbuster);
		Object obj = genericRepository.save(shop);
		System.out.println(obj);
	}

	@Test
	public void testAssignManagerToShop() {
		Shop shop = genericRepository.findById(Shop.class, 1);
		Employee employee = new Employee("xyz_manager", LocalDate.of(2000, 10, 5), EmploymentType.MANAGER,
				ShiftType.FULL_SHIFT, shop);
		employee = shopService.assignShopManager(employee);
		System.out.println(employee);
	}

	@Test
	public void testAssignCounterStaffToShop() {
		Shop shop = genericRepository.findById(Shop.class, 1);
		Employee employee = new Employee("test_staff_a", LocalDate.of(2001, 11, 6), EmploymentType.COUNTER_STAFF,
				ShiftType.MORNING_SHIFT, shop);
		employee = shopService.assignCouterStaff(employee);
		System.out.println(employee);
	}

	@Test
	public void testAssignCounterStaffsToShop() {
		Shop shop = genericRepository.findById(Shop.class, 1);
		Employee employee1 = new Employee("test_staff_a", LocalDate.of(2001, 11, 6), EmploymentType.COUNTER_STAFF,
				ShiftType.MORNING_SHIFT, shop);
		Employee employee2 = new Employee("test_staff_b", LocalDate.of(2002, 12, 7), EmploymentType.COUNTER_STAFF,
				ShiftType.EVENING_SHIFT, shop);

		List<Employee> emps = shopService.assignAllCouterStaffs(List.of(employee1, employee2));
		System.out.println(emps);
	}

	@Test
	public void testAddMovie() {
		Shop shop = genericRepository.findById(Shop.class, 1);
		Movie movie1 = new Movie("Movie_1", true, shop, 0);
		Movie movie2 = new Movie("Movie_2", true, shop, 15);
		Movie movie3 = new Movie("Movie_3", true, shop, 18);
		genericRepository.saveBatch(List.of(movie1, movie2, movie3));
	}

	@Test
	public void testAddCustomer() {
		LoanTypeDetail detail = genericRepository.findById(LoanTypeDetail.class, 1);
		Customer customer = new Customer("Cust_xyz", LocalDate.of(2000, 5, 6), detail);
		genericRepository.save(customer);
	}

	@Test
	public void testIssueMovieToCustomer() {
		Customer customer = genericRepository.findById(Customer.class, 1);
		List<Movie> movies = shopRepository.findMoviesByShopID(1);
		customerService.issueMoviesToCustomer(customer, movies, 30);
	}

	@Test
	public void testDepositMovieFromCustomer() {
		customerService.updateCustomerExpiredFieldByDuedate();
		Customer customer = genericRepository.findById(Customer.class, 1);
		List<Movie> movies = shopRepository.findMoviesByShopID(1);
		customerService.receiveMoviesFromCustomer(customer, movies, 30);
	}

	@Test
	public void testAvailableMoviesByShopID() {
		List<Movie> movies = shopRepository.findAvailableMoviesByShopID(1);
		System.out.println(movies);
	}

	@Test
	public void testFindCounterStaffDetailsByShopId() {
		List<Employee> employees = shopRepository.findCounterStaffByShopID(1);
		System.out.println(employees);
	}

	@Test
	public void testFindExpiredDueDateCustomers() {
		List<Customer> customers = customerRepository.findExpiredDueDateOfCustomersByCurrentDate();
		System.out.println(customers);
	}

}
