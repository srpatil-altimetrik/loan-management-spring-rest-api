package com.altimetrik.loan_management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoanRequestDTO {
	private int customerId;
	private int loanId;
	private double principalAmount;
	private double interestRate;
	private int loanTerm;
	private String loanType;
	private String loanStatus = "Pending";
	private boolean confirmApply; 
}
