package com.ibm.productservice.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDetails {

    private String name;
	private String description;
	private BigDecimal quantity;
	private BigDecimal price;
	private Set<Long> categories;
	public ProductDetails(String name, String description, BigDecimal quantity, BigDecimal price,
			Set<Long> categories) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.categories = categories;
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
	public Set<Long> getCategories() {
		return categories;
	}
	public void setCategories(Set<Long> categories) {
		this.categories = categories;
	}

	
	
}
