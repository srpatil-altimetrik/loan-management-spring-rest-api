package com.altimetrik.loan_management.service;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Loan;

public interface LoanService {
	
	String applyLoan(LoanRequestDTO loanRequestDTO);

	public void calculateInterest(int loanId);

	public String getLoanStatus(int loanId);

	public void calculateEMI(int loanId);

	public void loanRepayment(int loanId, double amount);

	public void getAllLoans(int loanId);

}
