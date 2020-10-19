package com.ibm.orderservice.service;

import com.ibm.orderservice.entity.Order;

public interface OrderService {
    public Order saveOrder(Order order);
}
