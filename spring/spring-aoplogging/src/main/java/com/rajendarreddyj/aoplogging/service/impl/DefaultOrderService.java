package com.rajendarreddyj.aoplogging.service.impl;

import org.springframework.stereotype.Service;

import com.rajendarreddyj.aoplogging.domain.Customer;
import com.rajendarreddyj.aoplogging.domain.Order;
import com.rajendarreddyj.aoplogging.domain.Product;
import com.rajendarreddyj.aoplogging.service.OrderService;

@Service
public class DefaultOrderService
  implements OrderService
{
  public Order order(Customer customer, Product product)
  {
    return new Order(Order.Status.PROCESSED, customer, product);
  }
}