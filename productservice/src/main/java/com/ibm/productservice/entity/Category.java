package com.ibm.productservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },   mappedBy = "categories")
    private Set<Product> products;

	public Category() {
		
	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Category(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
//		this.products=this.products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
	
	
	
}
