package com.cg.service;

import com.cg.entity.Product;

public interface ProductService {
	Product getProduct(Long id);

	Product addProduct(Product u);
}
