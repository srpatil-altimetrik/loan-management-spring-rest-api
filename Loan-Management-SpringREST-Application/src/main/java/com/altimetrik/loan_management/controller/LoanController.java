package com.altimetrik.loan_management.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.service.CustomerService;
import com.altimetrik.loan_management.service.LoanService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;

	@Autowired
	private CustomerService customerService;

	// Create a new loan
	/*
	 * @PostMapping("createLoan") public void createLoan(Customer customer, int
	 * loanId, String loanType, double loanAmount, int loanDuration, double
	 * interestRate) { loanService.createLoan(customer, loanId, loanType,
	 * loanAmount, loanDuration, interestRate); }
	 * 
	 * @PutMapping("updateLoan") public void updateLoan(int customerId, int loanId,
	 * String loanType, double loanAmount, int loanDuration, double interestRate) {
	 * loanService.updateLoan(customerId, loanId, loanType, loanAmount,
	 * loanDuration, interestRate); }
	 */

	// Apply for a loan
	@PostMapping("/applyLoan")
    public ResponseEntity<String> applyLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        try {
            String result = loanService.applyLoan(loanRequestDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Loan application failed.");
        }
    }
}
