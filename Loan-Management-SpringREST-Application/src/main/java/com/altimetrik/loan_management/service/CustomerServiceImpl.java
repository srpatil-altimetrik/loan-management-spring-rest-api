package com.altimetrik.loan_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Customer;
import com.altimetrik.loan_management.model.Loan;
import com.altimetrik.loan_management.repository.CustomerRepository;
import com.altimetrik.loan_management.repository.LoanRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	LoanRepository loanRepository;

	@Override
	public ResponseEntity<List<Customer>> getAllCustomers() {
		// TODO Auto-generated method stub
		try {
			List<Customer> customers = customerRepository.findAll();
			if (customers.isEmpty()) {
				return ResponseEntity.status(404).body(null);
			} else {
				return ResponseEntity.ok(customers);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@Override
	public ResponseEntity<String> addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
			Optional<Customer> existingCustomer = customerRepository.findById(customer.getCustomerId());
			if (existingCustomer.isPresent()) {
				return ResponseEntity.status(409).body("Customer already exists with ID: " + customer.getCustomerId());
			} else {
				customerRepository.save(customer);
				return ResponseEntity.status(201).body("Customer added successfully");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error adding customer: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Customer> getCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		try {
			Optional<Customer> customer = customerRepository.findById(customerId);
			if (customer.isPresent()) {
				return ResponseEntity.ok(customer.get());
			} else {
				return ResponseEntity.status(404).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}

	}

	@Override
	public ResponseEntity<String> updateCustomer(Integer customerId, Customer customer) {
		// TODO Auto-generated method stub
		try {
			Optional<Customer> existingCustomer = customerRepository.findById(customerId);
			if (existingCustomer.isPresent()) {
				customer.setCustomerId(customerId);
				customerRepository.save(customer);
				return ResponseEntity.ok("Customer updated successfully");
			} else {
				return ResponseEntity.status(404).body("Customer not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error updating customer: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> deleteCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		try {
			Optional<Loan> loan = loanRepository.findById(customerId);
			if (loan.isPresent()) {
				loanRepository.delete(loan.get());
				return ResponseEntity.ok("Customer deleted successfully");
			}
//			Optional<Customer> existingCustomer = customerRepository.findById(customerId);
//			if (existingCustomer.isPresent()) {
//				customerRepository.delete(existingCustomer.get());
//				return ResponseEntity.ok("Customer deleted successfully");
//			} 
			else {
				return ResponseEntity.status(404).body("Customer not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error deleting customer: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> apply(LoanRequestDTO loanRequestDTO) {
		// TODO Auto-generated method stub
		try {
			Optional<Customer> customer = customerRepository.findById(loanRequestDTO.getCustomerId());
			if (customer.isPresent()) {
				Loan loan = new Loan();
				loan.setCustomer(customer.get());
				loan.setPrincipalAmount(loanRequestDTO.getPrincipalAmount());
				loan.setInterestRate(loanRequestDTO.getInterestRate());
				loan.setLoanTerm(loanRequestDTO.getLoanTerm());
				loan.setLoanStatus("Pending");
				loanRepository.save(loan);
				return ResponseEntity.ok("Loan applied successfully");
			} else {
				return ResponseEntity.status(404).body("Customer not found with ID: " + loanRequestDTO.getCustomerId());
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error applying for loan: " + e.getMessage());
		}
	}
}
