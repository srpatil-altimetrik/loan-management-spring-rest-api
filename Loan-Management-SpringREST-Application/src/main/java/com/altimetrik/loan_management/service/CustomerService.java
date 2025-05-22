package com.altimetrik.loan_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.altimetrik.loan_management.model.Customer;

public interface CustomerService {

	public ResponseEntity<String> addCustomer(Customer customer);

	public ResponseEntity<List<Customer>> getAllCustomers();

	public ResponseEntity<Customer> getCustomerById(int customerId);

	public ResponseEntity<String> updateCustomer(int customerId, Customer customer);

	public ResponseEntity<String> deleteCustomer(int customerId);

}
