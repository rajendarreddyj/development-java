package com.rajendarreddyj.spring.data.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rajendarreddyj.spring.data.mongodb.model.Customer;
import com.rajendarreddyj.spring.data.mongodb.repository.CustomerRepository;

/**
 * Spring Boot will handle those repositories automatically as long as they are
 * included in the same package (or a sub-package) of your @SpringBootApplication
 * class
 * 
 * @author rajendarreddy
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Rajendar", "Jagapathi"));
		repository.save(new Customer("Rajendar1", "Jagapathi1"));
		repository.save(new Customer("Rajendar2", "Jagapathi3"));
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Rajendar'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Rajendar"));

		System.out.println("Customers found with findByLastName('Jagapathi'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Jagapathi")) {
			System.out.println(customer);
		}

	}

}
