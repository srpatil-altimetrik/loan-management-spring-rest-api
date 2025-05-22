package com.altimetrik.loan_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Loan;

public interface LoanService {

	public ResponseEntity<Object> applyLoan(LoanRequestDTO loanRequestDTO);

	public ResponseEntity<String> calculateInterest(Integer loanId);

	public ResponseEntity<String> getLoanStatus(Integer loanId);

	public ResponseEntity<String> calculateEMI(Integer loanId);

	public ResponseEntity<String> loanRepayment(Integer loanId, Double amount);

	public ResponseEntity<List<Loan>> getAllLoans();
	
	public ResponseEntity<String> getLoanById(Integer loanId);
}
