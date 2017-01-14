package com.rajendarreddyj.aoplogging.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rajendarreddyj.aoplogging.domain.Customer;
import com.rajendarreddyj.aoplogging.domain.Order;
import com.rajendarreddyj.aoplogging.domain.Product;
import com.rajendarreddyj.aoplogging.service.OrderService;

public class AopLoggingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        this.orderService = (ctx.getBean(OrderService.class));
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Customer customer = new Customer("Rajendarreddy Jagapathi", "Minneapolis");
        Product product = new Product(42L, "Travel Guide");

        Order order = this.orderService.order(customer, product);

        res.setHeader("Context-Type", "text/plain");
        PrintWriter writer = res.getWriter();
        writer.println("Thank you, your order has been received. Here it is:");
        writer.println();
        writer.println(order);
        writer.flush();
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.service(request, response);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.service(request, response);
    }
}