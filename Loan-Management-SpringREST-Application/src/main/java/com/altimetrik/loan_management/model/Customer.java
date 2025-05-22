package com.altimetrik.loan_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	private int customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhoneNo;
	private String customerAddress;
	private int customerCreditScore;
}
