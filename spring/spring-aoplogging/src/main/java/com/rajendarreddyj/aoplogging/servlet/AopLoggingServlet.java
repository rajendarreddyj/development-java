package com.rajendarreddyj.aoplogging.servlet;

import com.rajendarreddyj.aoplogging.domain.Customer;
import com.rajendarreddyj.aoplogging.domain.Order;
import com.rajendarreddyj.aoplogging.domain.Product;
import com.rajendarreddyj.aoplogging.service.OrderService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AopLoggingServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private OrderService orderService;
  
  public void init()
    throws ServletException
  {
    super.init();
    
    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    this.orderService = ((OrderService)ctx.getBean(OrderService.class));
  }
  
  protected void service(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
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
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    service(request, response);
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    service(request, response);
  }
}