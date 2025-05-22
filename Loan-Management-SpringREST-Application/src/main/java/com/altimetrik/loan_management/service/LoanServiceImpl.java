package com.altimetrik.loan_management.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String applyLoan(LoanRequestDTO loanRequestDTO) {
		System.out.println(loanRequestDTO.getCustomerId());
		Optional<Customer> customerOpt = customerRepository.findById(loanRequestDTO.getCustomerId());
		System.out.println(customerOpt);
		if (customerOpt.isEmpty()) {
			throw new NoSuchElementException("Customer not found.");
		}

		if (!loanRequestDTO.isConfirmApply()) {
			return "Loan application cancelled by user.";
		}

		Loan loan = new Loan();
		loan.setLoanId(loanRequestDTO.getLoanId());
		loan.setPrincipalAmount(loanRequestDTO.getPrincipalAmount());
		loan.setInterestRate(loanRequestDTO.getInterestRate());
		loan.setLoanTerm(loanRequestDTO.getLoanTerm());
		loan.setLoanType(loanRequestDTO.getLoanType());
		loan.setLoanStatus(loanRequestDTO.getLoanStatus());

		loan.setCustomer(customerOpt.get());
		loanRepository.save(loan);

		return "Loan applied successfully.";
	}

	@Override
	public void calculateInterest(int loanId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLoanStatus(int loanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateEMI(int loanId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loanRepayment(int loanId, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllLoans(int loanId) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void createLoan(Customer customer, int loanId, String
	 * loanType, double loanAmount, int loanDuration, double interestRate) { // TODO
	 * Auto-generated method stub Customer saveCustomer =
	 * customerRepository.save(customer); Loan loan = new Loan();
	 * loan.setCustomer(saveCustomer); loan.setLoanId(loanId);
	 * loan.setLoanType(loanType); loan.setPrincipalAmount(loanAmount);
	 * loan.setLoanTerm(loanDuration); loan.setInterestRate(interestRate);
	 * loan.setLoanStatus("Pending"); loanRepository.save(loan); }
	 * 
	 * @Override public void updateLoan(int customerId, int loanId, String loanType,
	 * double loanAmount, int loanDuration, double interestRate) { // TODO
	 * Auto-generated method stub Customer customer =
	 * customerRepository.findById(customerId).orElse(null); if (customer != null) {
	 * Loan loan = loanRepository.findById(loanId).orElse(null); if (loan != null) {
	 * loan.setCustomer(customer); loan.setLoanId(loanId);
	 * loan.setLoanType(loanType); loan.setPrincipalAmount(loanAmount);
	 * loan.setLoanTerm(loanDuration); loan.setInterestRate(interestRate);
	 * loanRepository.save(loan); } } }
	 */

}
