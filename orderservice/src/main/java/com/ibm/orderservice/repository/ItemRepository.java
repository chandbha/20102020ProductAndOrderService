package com.ibm.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.orderservice.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
