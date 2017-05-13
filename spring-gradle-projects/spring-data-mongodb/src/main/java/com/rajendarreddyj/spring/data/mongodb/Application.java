package com.rajendarreddyj.spring.data.mongodb;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rajendarreddyj.spring.data.mongodb.model.Customer;
import com.rajendarreddyj.spring.data.mongodb.repository.CustomerRepository;

/**
 * Spring Boot will handle those repositories automatically as long as they are included in the same package (or a
 * sub-package) of your @SpringBootApplication class
 * 
 * @author rajendarreddy
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = Logger.getAnonymousLogger();
    @Autowired
    private CustomerRepository repository;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {

        this.repository.deleteAll();

        // save a couple of customers
        this.repository.save(new Customer("Rajendar", "Jagapathi"));
        this.repository.save(new Customer("Rajendar1", "Jagapathi1"));
        this.repository.save(new Customer("Rajendar2", "Jagapathi3"));
        // fetch all customers
        logger.info("Customers found with findAll():");
        logger.info("-------------------------------");
        for (Customer customer : this.repository.findAll()) {
            logger.info(customer.toString());
        }

        // fetch an individual customer
        logger.info("Customer found with findByFirstName('Rajendar'):");
        logger.info("--------------------------------");
        logger.info(this.repository.findByFirstName("Rajendar").toString());

        logger.info("Customers found with findByLastName('Jagapathi'):");
        logger.info("--------------------------------");
        for (Customer customer : this.repository.findByLastName("Jagapathi")) {
            logger.info(customer.toString());
        }

    }

}
