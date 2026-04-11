package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.dto.OrderResponse;
import com.cg.dto.Product;
import com.cg.dto.User;
import com.cg.exception.ProductNotFoundException;
import com.cg.exception.UserNotFoundException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	RestTemplate restTemplate;

	private static Long orderCounter = 5000L;

	@Override
	@CircuitBreaker(name = "orderservice", fallbackMethod = "fallback")
	public OrderResponse createOrder(Long userId, Long productId, int quantity) {

		User user = restTemplate.getForObject("http://USER/users/" + userId, User.class);

		Product product = restTemplate.getForObject("http://PRODUCT/products/" + productId, Product.class);

		if (user == null)
			throw new UserNotFoundException("User not found with id: " + userId);

		if (product == null)
			throw new ProductNotFoundException("Product not found with id: " + productId);

		OrderResponse response = new OrderResponse();

		response.orderId = ++orderCounter;
		response.userName = user.name;
		response.productName = product.name;
		response.quantity = quantity;
		response.totalPrice = product.price * quantity;

		return response;
	}

	public OrderResponse fallback(Long userId, Long productId, int quantity, Exception ex) {
		OrderResponse res = new OrderResponse();
		res.userName = "Fallback User";
		res.productName = "Fallback Product";
		res.quantity = quantity;
		res.totalPrice = 0;
		return res;
	}

}
