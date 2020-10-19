package com.ibm.productservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.productservice.controller.ProductController;
import com.ibm.productservice.entity.Category;
import com.ibm.productservice.entity.Product;
import com.ibm.productservice.exception.RestEntityNotFoundException;

import com.ibm.productservice.repository.CategoryRepository;
import com.ibm.productservice.repository.ProductRepository;

@Service
public class ProductServices {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	Logger logger = LoggerFactory.getLogger(ProductServices.class);

	public List<Product> getAllProdcuts() {
		logger.info("Inside getAllProducts services");
		List<Product> productsList = new ArrayList<Product>();
		productsList = productRepository.findAll();
		logger.info("Products List received from repository " + productsList.size());
		return productsList == null ? Collections.emptyList() : productsList;
	}

	public List<Product> getAllProdcutsByCategoryId(Long categoryID) {
		List<Product> productsList = new ArrayList<Product>();
		Category category = categoryRepository.findById(categoryID).get();
		if (category.getProducts() == null) {
			return Collections.emptyList();
		} else {
			category.getProducts().forEach(productsList::add);
			return productsList;
		}

	}

	public Optional<Product> getProduct(@PathVariable Long id) {
		Optional<Product> products = productRepository.findById(id);
		if (products.isPresent()) {
			return products;
		} else {
			return Optional.empty();
		}
	}

	public Optional<Product> addProduct(Product products) {
		Product resultProduct = productRepository.save(products);
		return Optional.ofNullable(resultProduct);
	}

	public void updateProduct(Product products) {
		productRepository.save(products);
	}

	public void deleteProduct(Long id) {
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new RestEntityNotFoundException("Product ID " + id + " is not present ");
		}
	}
}
