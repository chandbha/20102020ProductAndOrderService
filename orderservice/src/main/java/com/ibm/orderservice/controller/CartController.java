package com.ibm.orderservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.orderservice.service.CartService;

@RestController
@RequestMapping("/orders/orderCart")
public class CartController {

    @Autowired
    CartService cartService;
    
   

    @GetMapping (value = "/cart")
    public ResponseEntity<List<Object>> getCart(@RequestHeader(value = "Cookie") String cartId){
        List<Object> cart = cartService.getCart(cartId);
        if(!cart.isEmpty()) {
        	return new ResponseEntity<List<Object>>(
        			cart,
        			
        			HttpStatus.OK);
        }
    	return new ResponseEntity<List<Object>>(
    			
    			HttpStatus.NOT_FOUND);  
    }

    @PostMapping(value = "/cart", params = {"productId", "quantity"})
    public ResponseEntity<List<Object>> addItemToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestHeader(value = "Cookie") String cartId,
            HttpServletRequest request) {
        List<Object> cart = cartService.getCart(cartId);
        if(cart != null) {
        	if(cart.isEmpty()){
        		cartService.addItemToCart(cartId, productId, quantity);
        	}else{
        		if(cartService.checkIfItemIsExist(cartId, productId)){
        			cartService.changeItemQuantity(cartId, productId, quantity);
        		}else {
        			cartService.addItemToCart(cartId, productId, quantity);
        		}
        	}
        	return new ResponseEntity<List<Object>>(
        			cart,
        			
        			HttpStatus.CREATED);
        }
        return new ResponseEntity<List<Object>>(
        		HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/cart", params = "productId")
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestHeader(value = "Cookie") String cartId){
    	List<Object> cart = cartService.getCart(cartId);
    	if(cart != null) {
    		cartService.deleteItemFromCart(cartId, productId);
            return new ResponseEntity<Void>(
            		
            		HttpStatus.OK);
    	}
        return new ResponseEntity<Void>(
        		
        		HttpStatus.NOT_FOUND);
    }
}
