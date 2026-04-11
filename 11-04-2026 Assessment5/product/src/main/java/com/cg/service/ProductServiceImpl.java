package com.cg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Product;
import com.cg.exception.ProductNotFoundException;
import com.cg.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repo;

	@Override
	public Product getProduct(Long id) {
		return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

	}

	@Override
	public Product addProduct(Product p) {
		return repo.save(p);
	}

}
