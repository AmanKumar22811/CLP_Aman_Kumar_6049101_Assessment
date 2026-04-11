package com.cg.service;

import com.cg.dto.OrderResponse;

public interface OrderService {
	OrderResponse createOrder(Long userId, Long productId, int quantity);
}
