package com.blockbuster.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_customer_transaction")
public class CustomerTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	private LocalDateTime transactionDateTime;

	private double amount;

	private String transactionType;

	public CustomerTransaction() {

	}

	public CustomerTransaction(Movie movie, LocalDateTime transactionDateTime, double amount, String transactionType) {
		super();
		this.movie = movie;
		this.transactionDateTime = transactionDateTime;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
