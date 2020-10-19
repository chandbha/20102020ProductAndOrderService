package com.ibm.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.productservice.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
