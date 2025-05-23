package com.altimetrik.loan_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Customer;
import com.altimetrik.loan_management.repository.LoanRepository;
import com.altimetrik.loan_management.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/getCustomerById/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}
	
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId) {
		return customerService.deleteCustomerById(customerId);
	}
	
	@PostMapping("/apply")
	public ResponseEntity<String> apply(@RequestBody LoanRequestDTO loanRequestDTO) {
		return customerService.apply(loanRequestDTO);
	}
}
