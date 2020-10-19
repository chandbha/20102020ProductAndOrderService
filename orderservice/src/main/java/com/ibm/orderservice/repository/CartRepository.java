package com.ibm.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.orderservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
