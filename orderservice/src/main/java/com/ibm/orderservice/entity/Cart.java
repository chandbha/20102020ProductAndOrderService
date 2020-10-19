package com.ibm.orderservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "cart")
public class Cart {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long cartId;

    @Column (name = "quantity")
    @NotNull
    private int quantity;


    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToMany (mappedBy = "items")
    @JsonIgnore
    private List<Item> items;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
    
    
}
