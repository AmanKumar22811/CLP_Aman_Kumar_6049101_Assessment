package com.cg.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Loan;
import com.cg.service.LoanService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@PostMapping
	public Loan createLoan(@Valid @RequestBody Loan loan) {
		return loanService.createLoan(loan);
	}

	@GetMapping
	public List<Loan> getAllLoans() {
		return loanService.getAllLoans();
	}

	@GetMapping("/{id}")
	public Loan getLoanById(@PathVariable Long id) {
		return loanService.getLoanById(id);
	}

	@PutMapping("/{id}/status")
	public Loan updateStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
		return loanService.updateLoanStatus(id, request.get("status"));
	}
}
