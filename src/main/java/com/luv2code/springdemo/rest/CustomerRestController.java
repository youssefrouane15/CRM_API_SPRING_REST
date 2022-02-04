package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the customer service

	@Autowired
	private CustomerService customerService;

	// add mapping for GET / customers

	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerService.getCustomers();
	}

	// add mapping for GET / customers/{customerId}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		if (customer == null) {
			throw new CustomerNotFoundException("customer id not found - :" + customerId);
		}

		return customer;
	}
	
	// add mapping for POST /customers to add a new customer
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		
		// setting the id to 0 forces hibernate to treat as null or empty id so it will knows that this entity does 
		// not exist on the db, in this case it will insert it nor update it
		
		customer.setId(0);
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	// add mapping for PUT /customers to update a customer
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	// add mapping for DELETE /customers/{customerId} to delete a customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer foundCustomer = customerService.getCustomer(customerId);
		
		if (foundCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - :" + customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted Customer - " + customerId; 
	}

	
}
