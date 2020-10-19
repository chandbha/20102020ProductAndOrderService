package com.ibm.orderservice.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.orderservice.entity.Item;
import com.ibm.orderservice.entity.Order;
import com.ibm.orderservice.entity.User;
import com.ibm.orderservice.restclient.UserClient;
import com.ibm.orderservice.service.CartService;
import com.ibm.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    
    @PostMapping(value = "/order/{userId}")
    public ResponseEntity<Order> saveOrder(
    		@PathVariable("userId") Long userId,
    		@RequestHeader(value = "Cookie") String cartId,
    		HttpServletRequest request){
    	
        List<Item> cart = cartService.getAllItemsFromCart(cartId);
        User user = userClient.getUserById(userId);   
        if(cart != null && user != null) {
        	Order order = this.createOrder(cart, user);
        	try{
                orderService.saveOrder(order);
                cartService.deleteCart(cartId);
                return new ResponseEntity<Order>(
                		order, 
                		HttpStatus.CREATED);
            }catch (Exception ex){
                ex.printStackTrace();
                return new ResponseEntity<Order>(
                		
                		HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
  
        return new ResponseEntity<Order>(
        		
        		HttpStatus.NOT_FOUND);
    }
    
    private Order createOrder(List<Item> cart, User user) {
        Order order = new Order();
        order.setItems(cart);
        order.setUser(user);
        order.setTotal(countTotalPrice(cart));
        order.setOrderedDate(LocalDate.now());
        order.setStatus("PAYMENT_EXPECTED");
        return order;
    }
    
    public  BigDecimal countTotalPrice(List<Item> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total = total.add(cart.get(i).getSubTotal());
        }
        return total;
    }
}
