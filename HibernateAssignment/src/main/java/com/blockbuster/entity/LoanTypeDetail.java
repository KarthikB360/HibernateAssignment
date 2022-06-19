package com.blockbuster.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_loan_type")
public class LoanTypeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LoanType loanType;

	public static enum LoanType {
		BASIC, STANDARD, PREMIUM
	};

	private double loanAmount;

	private int loanPeriod;

	@OneToMany(mappedBy = "loanType")
	private List<Customer> customers;

	public LoanTypeDetail() {

	}

	public LoanTypeDetail(LoanType loanType, double loanAmount, int loanPeriod) {
		super();
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.loanPeriod = loanPeriod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
