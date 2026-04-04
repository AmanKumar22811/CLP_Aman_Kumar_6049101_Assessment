package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Emp;
import com.cg.repository.EmpRepository;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepository repo;

	public List<Emp> getAll() {
		return repo.findAll();
	}

	public Emp getById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	public void save(Emp emp) {
		repo.save(emp);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
