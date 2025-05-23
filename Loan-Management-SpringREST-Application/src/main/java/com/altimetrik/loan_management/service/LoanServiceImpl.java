package com.altimetrik.loan_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Customer;
import com.altimetrik.loan_management.model.Loan;
import com.altimetrik.loan_management.repository.CustomerRepository;
import com.altimetrik.loan_management.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseEntity<Object> applyLoan(LoanRequestDTO loanRequestDTO) {
		// TODO Auto-generated method stub
		if (loanRequestDTO.getCustomerId() == null || loanRequestDTO.getCustomerId() == null) {
		    throw new IllegalArgumentException("Customer ID must not be null");
		}
		
	    try {
	        Optional<Customer> customerOptional = customerRepository.findById(loanRequestDTO.getCustomerId());
	        if (customerOptional.isPresent()) {
	            Customer customer = customerOptional.get();
	            Loan loan = new Loan();

	            loan.setCustomer(customer);
	            loan.setPrincipalAmount(loanRequestDTO.getPrincipalAmount());
	            loan.setInterestRate(loanRequestDTO.getInterestRate());
	            loan.setLoanTerm(loanRequestDTO.getLoanTerm());
	            loan.setLoanType(loanRequestDTO.getLoanType());
	            loan.setLoanStatus("Pending");

	            Loan savedLoan = loanRepository.save(loan);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedLoan);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Customer not found with ID: " + loanRequestDTO.getCustomerId());
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error applying for loan: " + e.getMessage());
	    }
	}


	@Override
	public ResponseEntity<String> calculateInterest(Integer loanId) {
		try {
			Optional<Loan> loanOptional = loanRepository.findById(loanId);
			if (loanOptional.isPresent()) {
				Loan loan = loanOptional.get();
				double principal = loan.getPrincipalAmount();
				double rate = loan.getInterestRate();
				int tenure = loan.getLoanTerm();

				double interest = calculateInterest(principal, rate, tenure);
				return ResponseEntity.ok("Calculated interest: " + (int)interest);
				
			} else {
				return ResponseEntity.status(404).body("Loan not found with ID: " + loanId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error calculating interest: " + e.getMessage());
		}
	}

	// Overloaded method
	public double calculateInterest(double principal, double rate, int tenure) {
		
		double interest = (principal * rate * tenure) / 12;
		return interest;
	}

	@Override
	public ResponseEntity<String> getLoanStatus(Integer loanId) {
		// TODO Auto-generated method stub
		try {
			Optional<Loan> loanOptional = loanRepository.findById(loanId);
			if (loanOptional.isPresent()) {
				Loan loan = loanOptional.get();
				Customer customer = loan.getCustomer();
				int creditScore = customer.getCustomerCreditScore();

				String status;
				if (creditScore > 650) {
					status = "Approved";
				} else {
					status = "Rejected";
				}
				loan.setLoanStatus(status);
				loanRepository.save(loan);

				return ResponseEntity.ok("Loan status: " + status);
			} else {
				return ResponseEntity.status(404).body("Loan not found with ID: " + loanId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error fetching loan status: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> calculateEMI(Integer loanId) {
		// TODO Auto-generated method stub
		try {
			Optional<Loan> loanOptional = loanRepository.findById(loanId);
			if (loanOptional.isPresent()) {
				Loan loan = loanOptional.get();
				double principal = loan.getPrincipalAmount();
				double rate = loan.getInterestRate() / 12 / 100;
				int tenure = loan.getLoanTerm();

				double emi = (principal * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
				return ResponseEntity.ok("Calculated EMI: " + emi);
			} else {
				return ResponseEntity.status(404).body("Loan not found with ID: " + loanId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error calculating EMI: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> loanRepayment(Integer loanId, Double amount) {
		// TODO Auto-generated method stub
		try {
			Optional<Loan> loanOptional = loanRepository.findById(loanId);
			if (loanOptional.isPresent()) {
				Loan loan = loanOptional.get();
				double principal = loan.getPrincipalAmount();
				double remainingAmount = principal - amount;

				if (remainingAmount <= 0) {
					loan.setLoanStatus("Closed");
					loan.setPrincipalAmount(0.0);
				} else {
					loan.setPrincipalAmount(remainingAmount);
				}
				loanRepository.save(loan);
				return ResponseEntity.ok("Loan repayment successful. Remaining amount: " + remainingAmount);
			} else {
				return ResponseEntity.status(404).body("Loan not found with ID: " + loanId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error processing loan repayment: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<List<Loan>> getAllLoans() {
		// TODO Auto-generated method stub
		try {
			List<Loan> loans = loanRepository.findAll();
			if (loans.isEmpty()) {
				return ResponseEntity.status(404).body(null);
			} else {
				return ResponseEntity.ok(loans);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@Override
	public ResponseEntity<String> getLoanById(Integer loanId) {
		// TODO Auto-generated method stub
		try {
			Optional<Loan> loanOptional = loanRepository.findById(loanId);
			if (loanOptional.isPresent()) {
				return ResponseEntity.ok(loanOptional.get().toString());
			} else {
				return ResponseEntity.status(404).body("Loan not found with ID: " + loanId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error fetching loan by ID: " + e.getMessage());
		}
	}

}
