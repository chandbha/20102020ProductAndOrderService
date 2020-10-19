package com.ibm.orderservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.orderservice.entity.Product;


@FeignClient(name = "product-service")
public interface ProductClient {

	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id);

}
