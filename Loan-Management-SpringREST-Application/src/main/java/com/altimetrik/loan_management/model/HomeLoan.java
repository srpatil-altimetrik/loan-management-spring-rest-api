package com.altimetrik.loan_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeLoan extends Loan {
	private String propertyAddress;
	private int propertyValue;
}
