package com.altimetrik.loan_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.loan_management.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
