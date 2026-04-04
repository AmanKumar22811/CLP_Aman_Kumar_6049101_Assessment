package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.util.List;

import com.cg.dto.EmpDTO;
import com.cg.entity.Emp;
import com.cg.service.EmpService;

@Controller
public class EmpController {

	@Autowired
	private EmpService service;

	@GetMapping("/viewall")
	public String viewAll(Model model) {
		List<Emp> list = service.getAll();
		model.addAttribute("emps", list);
		return "view";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Emp emp = service.getById(id);

		EmpDTO dto = new EmpDTO();
		dto.setEmpId(emp.getEmpId());
		dto.setEmpName(emp.getEmpName());
		dto.setEmpSal(emp.getEmpSal());
		dto.setEmpDoj(emp.getEmpDoj());
		dto.setDeptName(emp.getDeptName());

		model.addAttribute("emp", dto);
		return "edit";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("emp") EmpDTO dto, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "edit";
		}

		Emp emp = new Emp();
		emp.setEmpId(dto.getEmpId());
		emp.setEmpName(dto.getEmpName());
		emp.setEmpSal(dto.getEmpSal());
		emp.setEmpDoj(dto.getEmpDoj());
		emp.setDeptName(dto.getDeptName());

		service.save(emp);

		redirectAttributes.addFlashAttribute("msg", "Employee Updated Successfully");
		return "redirect:/viewall";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		service.delete(id);
		redirectAttributes.addFlashAttribute("msg", "Employee Deleted Successfully");
		return "redirect:/viewall";
	}
}