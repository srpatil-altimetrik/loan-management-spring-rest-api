package com.altimetrik.loan_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	@Id
	private Integer loanId;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	private Double principalAmount;
	private Double interestRate;
	private Integer loanTerm;
	private String loanType;
	private String loanStatus;
}
