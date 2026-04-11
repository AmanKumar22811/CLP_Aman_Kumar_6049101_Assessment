package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Product;
import com.cg.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductServiceImpl service;

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		return service.getProduct(id);
	}

	@PostMapping
	public Product addProduct(@RequestBody Product p) {
		return service.addProduct(p);
	}
}
