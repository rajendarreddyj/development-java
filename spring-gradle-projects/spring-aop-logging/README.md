aoplogging Project
=================
This projects shows how to setup and use aop logging in a java webapp.

To run the project:

$ gradle TomcatRun

or

$ gralde jettyRun

Fire up this url in your browser: http://localhost:8080/spring-aop-logging/

You should see something like the following in your console each time you refresh the web page:

aoplogging: 22:46:26.848 [http-nio-8080-exec-3] DEBUG com.rajendarreddyj.aoplogging.service.impl.DefaultOrderService - order(): [Customer{fullName='Rajendarreddy Jagapathi', address='Minneapolis'}, Product{id=42, name='Travel Guide'}]
aoplogging: 22:46:26.851 [http-nio-8080-exec-3] DEBUG com.rajendarreddyj.aoplogging.service.impl.DefaultOrderService - order(): result=Order{status=PROCESSED, timestamp=Sun May 03 22:46:26 CDT 2015, customer=Customer{fullName='Rajendarreddy Jagapathi', address='Minneapolis'}, product=Product{id=42, name='Travel Guide'}}
