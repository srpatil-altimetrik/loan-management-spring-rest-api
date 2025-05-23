package com.altimetrik.loan_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Customer;

public interface CustomerService {

	public ResponseEntity<String> apply(LoanRequestDTO loanRequestDTO);
	public ResponseEntity<String> addCustomer(Customer customer);

	public ResponseEntity<List<Customer>> getAllCustomers();

	public ResponseEntity<Customer> getCustomerById(Integer customerId);

	public ResponseEntity<String> updateCustomer(Integer customerId, Customer customer);

	public ResponseEntity<String> deleteCustomerById(Integer customerId);

}
