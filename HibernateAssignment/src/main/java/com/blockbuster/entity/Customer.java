package com.blockbuster.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private LocalDate dob;

	@OneToOne(mappedBy = "customer")
	private Address address;

	@ManyToOne
	@JoinColumn(name = "loan_type_id")
	private LoanTypeDetail loanType;

	private boolean borrowed;

	private boolean expired;

	private LocalDateTime dueDate;
	
	@OneToMany(mappedBy = "customer")
	private List<Movie> movies;

	public Customer() {

	}

	public Customer(String name, LocalDate dob, LoanTypeDetail loanType) {
		super();
		this.name = name;
		this.dob = dob;
		this.loanType = loanType;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LoanTypeDetail getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanTypeDetail loanType) {
		this.loanType = loanType;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + ", borrowed=" + borrowed + ", expired="
				+ expired + ", dueDate=" + dueDate + "]";
	}

}
