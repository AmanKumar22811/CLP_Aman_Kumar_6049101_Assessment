package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Loan;
import com.cg.exception.DuplicateLoanApplicationException;
import com.cg.exception.InvalidLoanAmountException;
import com.cg.exception.LoanNotFoundException;
import com.cg.exception.LoanStatusException;
import com.cg.repository.LoanRepository;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Loan createLoan(Loan loan) {

		if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
			throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
		}

		loanRepository.findByApplicantNameAndStatus(loan.getApplicantName(), "PENDING").ifPresent(l -> {
			throw new DuplicateLoanApplicationException("User already has a pending loan");
		});

		loan.setStatus("PENDING");
		return loanRepository.save(loan);
	}

	@Override
	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}

	@Override
	public Loan getLoanById(Long id) {
		return loanRepository.findById(id)
				.orElseThrow(() -> new LoanNotFoundException("Loan not found with ID : " + id));
	}

	@Override
	public Loan updateLoanStatus(Long id, String status) {

		Loan loan = getLoanById(id);

		if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
			throw new LoanStatusException("Status must be APPROVED or REJECTED");
		}

		loan.setStatus(status);
		return loanRepository.save(loan);
	}
}
