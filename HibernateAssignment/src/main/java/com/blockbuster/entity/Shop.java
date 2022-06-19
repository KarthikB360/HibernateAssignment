package com.blockbuster.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_shop")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String location;

	@ManyToOne
	@JoinColumn(name = "blockbuster_id")
	private Blockbuster blockbuster;

	@OneToMany(mappedBy = "shop")
	private List<Employee> employees;

	@OneToMany(mappedBy = "shop")
	private List<Movie> movies;

	public Shop() {

	}

	public Shop(String location, Blockbuster blockbuster) {
		super();
		this.location = location;
		this.blockbuster = blockbuster;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Blockbuster getBlockbuster() {
		return blockbuster;
	}

	public void setBlockbuster(Blockbuster blockbuster) {
		this.blockbuster = blockbuster;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
