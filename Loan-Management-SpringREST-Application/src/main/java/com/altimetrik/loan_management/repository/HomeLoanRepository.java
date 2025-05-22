package com.altimetrik.loan_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.loan_management.model.HomeLoan;
import com.altimetrik.loan_management.model.Loan;

@Repository
public interface HomeLoanRepository extends JpaRepository<HomeLoan, Integer> {

}
