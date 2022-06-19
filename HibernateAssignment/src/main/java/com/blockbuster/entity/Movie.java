package com.blockbuster.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private boolean inStock;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;

	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	private int certificate;

	public Movie() {

	}

	public Movie(String name, boolean inStock, Shop shop, int certificate) {
		super();
		this.name = name;
		this.inStock = inStock;
		this.shop = shop;
		this.certificate = certificate;
	}

	public Movie(String name, boolean inStock, Shop shop, Customer customer, int certificate) {
		super();
		this.name = name;
		this.inStock = inStock;
		this.shop = shop;
		this.customer = customer;
		this.certificate = certificate;
	}

	public int getCertificate() {
		return certificate;
	}

	public void setCertificate(int certificate) {
		this.certificate = certificate;
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

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
