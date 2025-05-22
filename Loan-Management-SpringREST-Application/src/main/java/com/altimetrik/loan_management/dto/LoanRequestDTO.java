package com.altimetrik.loan_management.dto;

import lombok.Data;

@Data
public class LoanRequestDTO {
	private Integer customerId;
	private Integer loanId;
    private Double principalAmount;
    private Double interestRate;
    private Integer loanTerm;
    private String loanType;
    private String loanStatus;
}
