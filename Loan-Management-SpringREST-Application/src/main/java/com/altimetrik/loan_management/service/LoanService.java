package com.altimetrik.loan_management.service;

import org.springframework.http.ResponseEntity;

import com.altimetrik.loan_management.model.Loan;

public interface LoanService {
	
//	public ResponseEntity<String> applyLoan(int customerId, Loan loan);
	public ResponseEntity<String> applyLoan(Loan loan);
	public ResponseEntity<String> calculateInterest(Integer loanId);

	public String getLoanStatus(int loanId);

	public void calculateEMI(int loanId);

	public void loanRepayment(int loanId, double amount);

	public void getAllLoans(int loanId);

}
