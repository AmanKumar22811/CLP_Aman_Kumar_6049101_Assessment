package com.cg.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmpDTO {

	private Integer empId;

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 25, message = "Name must be 3-25 chars")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Only alphabets allowed")
	private String empName;

	@NotNull(message = "Salary required")
	@Min(value = 1000, message = "Min salary 1000")
	@Max(value = 500000, message = "Max salary 500000")
	private Double empSal;

	@NotNull(message = "DOJ required")
	@FutureOrPresent(message = "Date must be today or future")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate empDoj;

	@Pattern(regexp = "^(hr|production)$", message = "Dept must be hr or production")
	private String deptName;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSal() {
		return empSal;
	}

	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}

	public LocalDate getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(LocalDate empDoj) {
		this.empDoj = empDoj;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
