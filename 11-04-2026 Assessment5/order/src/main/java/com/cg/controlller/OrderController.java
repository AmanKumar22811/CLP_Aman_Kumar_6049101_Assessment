package com.cg.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.OrderRequest;
import com.cg.dto.OrderResponse;
import com.cg.service.OrderServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl service;

	@PostMapping
	public OrderResponse createOrder(@RequestBody OrderRequest req) {
		if (req.quantity <= 0) {
			throw new RuntimeException("Quantity must be > 0");
		}
		return service.createOrder(req.userId, req.productId, req.quantity);
	}
}
