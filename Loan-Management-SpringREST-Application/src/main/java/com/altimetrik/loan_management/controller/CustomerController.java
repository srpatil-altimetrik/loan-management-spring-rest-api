package com.altimetrik.loan_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.loan_management.model.Customer;
import com.altimetrik.loan_management.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/getCustomerById/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
		return customerService.deleteCustomer(customerId);
	}
}
