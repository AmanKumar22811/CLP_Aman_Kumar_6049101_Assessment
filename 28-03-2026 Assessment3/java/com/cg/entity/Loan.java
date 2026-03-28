package com.cg.entity;

import jakarta.persistence.*;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String applicantName;

	private double loanAmount;

	private String status;

	public Loan() {
	}

	public Loan(Long id, String applicantName, double loanAmount, String status) {
		this.id = id;
		this.applicantName = applicantName;
		this.loanAmount = loanAmount;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}