package com.altimetrik.loan_management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.loan_management.model.Customer;
import com.altimetrik.loan_management.model.Loan;
import com.altimetrik.loan_management.repository.CustomerRepository;
import com.altimetrik.loan_management.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseEntity<String> applyLoan(Loan loan) {
		// TODO Auto-generated method stub
		try {
			Optional<Customer> customerOptional = customerRepository.findById(loan.getCustomer().getCustomerId());
			System.out.println("Customer ID: " + loan.getCustomer().getCustomerId());
			if (customerOptional.isPresent()) {
				Customer customer = customerOptional.get();
				loan.setCustomer(customer);
				loanRepository.save(loan);
				return ResponseEntity.status(201).body("Loan applied successfully");
			} else {
				return ResponseEntity.status(404).body("Customer not found with ID: " + loan.getCustomer().getCustomerId());
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error applying loan: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> calculateInterest(Integer loanId) {
		// TODO Auto-generated method stub
		try {
			Optional<Loan> loanOptional = loanRepository.findById(loanId);
			if (loanOptional.isPresent()) {
				Loan loan = loanOptional.get();
				double principal = loan.getPrincipalAmount();
				double rate = loan.getInterestRate();
				int term = loan.getLoanTerm();

				double interest = (principal * rate * term) / 100;
				return ResponseEntity.ok("Calculated interest: " + interest);
			} else {
				return ResponseEntity.status(404).body("Loan not found with ID: " + loanId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error calculating interest: " + e.getMessage());
		}
	}

	@Override
	public String getLoanStatus(int loanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateEMI(int loanId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loanRepayment(int loanId, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllLoans(int loanId) {
		// TODO Auto-generated method stub

	}
}
