package com.altimetrik.loan_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.loan_management.dto.LoanRequestDTO;
import com.altimetrik.loan_management.model.Loan;
import com.altimetrik.loan_management.service.CustomerService;
import com.altimetrik.loan_management.service.LoanService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@Autowired
	private CustomerService customerService;

//	@Autowired
//	private RestTemplate restTemplate;

//	@PostMapping("/apply")
//	public ResponseEntity<String> applyLoan(@RequestBody LoanRequestDTO dto) {
//		String url = "http://localhost:8081/loans/apply";
//		ResponseEntity<String> response = restTemplate.postForEntity(url, dto, String.class);
//		return response;
//	}
	
	@PostMapping("/apply")
	public ResponseEntity<Object> applyLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
		return loanService.applyLoan(loanRequestDTO);
	}
	
	@GetMapping("/calculateInterest/{loanId}")
	public ResponseEntity<String> calculateInterest(@PathVariable Integer loanId) {
		return loanService.calculateInterest(loanId);
	}
	
	
	@GetMapping("/getLoanStatus/{loanId}")
	public ResponseEntity<String> getLoanStatus(@PathVariable Integer loanId) {
		return loanService.getLoanStatus(loanId);
	}
	
	@GetMapping("/calculateEMI/{loanId}")
	public ResponseEntity<String> calculateEMI(@PathVariable Integer loanId) {
		return loanService.calculateEMI(loanId);
	}
	
	@GetMapping("/loanRepayment/{loanId}/{amount}")
	public ResponseEntity<String> loanRepayment(@PathVariable Integer loanId, @PathVariable Double amount) {
		return loanService.loanRepayment(loanId, amount);
	}
	
	@GetMapping("/getLoanById/{loanId}")
	public ResponseEntity<String> getLoanById(@PathVariable Integer loanId) {
		return loanService.getLoanById(loanId);
	}
	
	@GetMapping("/getAllLoans")
	public ResponseEntity<List<Loan>> getAllLoan() {
		return loanService.getAllLoans();
	}
}
