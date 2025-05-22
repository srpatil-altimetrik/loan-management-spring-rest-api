package com.altimetrik.loan_management.dto;

import lombok.Data;

@Data
public class LoanRequestDTO {
	private int loanId;
    private double principalAmount;
    private double interestRate;
    private int loanTerm;
    private String loanType;
    private String loanStatus;
    private int customerId;
}
