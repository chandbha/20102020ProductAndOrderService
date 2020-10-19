package com.ibm.productservice.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "quantity")
	private BigDecimal quantity;
	@Column(name = "price")
	private BigDecimal price;
	
	@JsonManagedReference
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories;

	public Product() {

	}
	
	public Product(long id, String name, String description, BigDecimal quantity, BigDecimal price,
			Set<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.categories = categories;
	}



	public Product(String name, String description, BigDecimal quantity, BigDecimal price, Set<Category> categories) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.categories = categories;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}


	

}
