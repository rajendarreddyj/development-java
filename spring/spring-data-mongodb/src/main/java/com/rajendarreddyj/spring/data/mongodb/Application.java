package com.rajendarreddyj.spring.data.mongodb;

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
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : this.repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Rajendar'):");
        System.out.println("--------------------------------");
        System.out.println(this.repository.findByFirstName("Rajendar"));

        System.out.println("Customers found with findByLastName('Jagapathi'):");
        System.out.println("--------------------------------");
        for (Customer customer : this.repository.findByLastName("Jagapathi")) {
            System.out.println(customer);
        }

    }

}
