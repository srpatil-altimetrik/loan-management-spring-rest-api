package com.altimetrik.loan_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "car_loan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarLoan extends Loan{
	private String carModel;
	private int carValue;

}
