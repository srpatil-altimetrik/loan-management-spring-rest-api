package com.altimetrik.loan_management.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Loan {
	@Id
	private int loanId;
	
	@ManyToOne(cascade = {CascadeType.ALL})	
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	private double principalAmount;
	private double interestRate;
	private int loanTerm;
	private String loanType;
	private String loanStatus;
}
