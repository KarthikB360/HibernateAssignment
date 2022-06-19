package com.blockbuster.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private LocalDate dob;

	private EmploymentType employmentType;

	private ShiftType shiftType;

	public static enum EmploymentType {
		MANAGER, COUNTER_STAFF
	};

	public static enum ShiftType {
		FULL_SHIFT, MORNING_SHIFT, EVENING_SHIFT
	};

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;

	public Employee() {

	}

	public Employee(String name, LocalDate dob, EmploymentType employmentType, ShiftType shiftType, Shop shop) {
		super();
		this.name = name;
		this.dob = dob;
		this.employmentType = employmentType;
		this.shiftType = shiftType;
		this.shop = shop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public ShiftType getShiftType() {
		return shiftType;
	}

	public void setShiftType(ShiftType shiftType) {
		this.shiftType = shiftType;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + ", employmentType=" + employmentType
				+ ", shiftType=" + shiftType + "]";
	}

}
