package com.ibm.productservice.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.productservice.dto.ProductDetails;
import com.ibm.productservice.entity.Category;
import com.ibm.productservice.entity.Product;
import com.ibm.productservice.exception.RestEntityNotFoundException;
import com.ibm.productservice.exception.ServerErrorException;
import com.ibm.productservice.service.CategoryService;
import com.ibm.productservice.service.ProductServices;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@RequestMapping("/products")
@EnableSwagger2
public class ProductController {
	
	@Autowired
	private ProductServices productsService;

	@Autowired
	private CategoryService categoryService;
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	 

	
	@GetMapping(value = "/")
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.info("Inside getAllProducts");
		try {
			List<Product> products = productsService.getAllProdcuts();
			if (!products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(products);
			} else {
				logger.info("No Products not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception ex) {
			logger.error("Exception occured in getAllProducts " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/categories/{categoryId}/products")
	public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable Long categoryId) {
		logger.info("Inside getAllProductsByCategoryId");
		try {
			List<Product> products = productsService.getAllProdcutsByCategoryId(categoryId);
			if (!products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(products);
			} else {
				logger.info("The category id is not valid ");
				throw new RestEntityNotFoundException("Please enter valid Category ID.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		try {
			Optional<Product> product = productsService.getProduct(id);
			if (product.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(product.get());
			} else {
				throw new RestEntityNotFoundException("Please enter Valid Product ID");
			}
		} catch (Exception e) {
			throw new ServerErrorException("Unable to get Products. Please try again Later!!!");
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Product> addProducts(@RequestBody ProductDetails productdetails) {
		try {

			Set<Long> categoryIds = productdetails.getCategories();
			Set<Category> categories = new HashSet<Category>();
			for (Long id : categoryIds) {
				categories.add(categoryService.getCategory(id));
			}
			if(categories.isEmpty()) {
				throw new RestEntityNotFoundException("Entered Categories doesn't Exist");
			}
			Product product = new Product(productdetails.getName(), productdetails.getDescription(),
					productdetails.getQuantity(), productdetails.getPrice(), categories);
			Optional<Product> savedProduct = productsService.addProduct(product);
		
			if (savedProduct.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(product);
			} else {
				throw new ServerErrorException("Unable to add Products details to the Database. Please try again Later!!!");
			}

		} catch (Exception e) {
			throw new ServerErrorException("Unable to add Products details to the Database due to " + e.getMessage());
		}
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
	public String deleteProducts(@PathVariable Long id) {
			productsService.deleteProduct(id);
			return "Deleted";
	}
}
