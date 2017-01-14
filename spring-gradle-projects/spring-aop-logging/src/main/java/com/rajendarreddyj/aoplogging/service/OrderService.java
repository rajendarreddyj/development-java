package com.rajendarreddyj.aoplogging.service;

import com.rajendarreddyj.aoplogging.domain.Customer;
import com.rajendarreddyj.aoplogging.domain.Order;
import com.rajendarreddyj.aoplogging.domain.Product;

public abstract interface OrderService {
    public abstract Order order(Customer paramCustomer, Product paramProduct);
}