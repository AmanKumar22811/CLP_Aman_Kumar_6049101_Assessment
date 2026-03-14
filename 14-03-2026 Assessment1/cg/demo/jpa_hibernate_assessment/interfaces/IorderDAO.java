package cg.demo.jpa_hibernate_assessment.interfaces;

import java.util.List;

import cg.demo.jpa_hibernate_assessment.entity.Order;

public interface IorderDAO {
	public boolean addOrder(Order order,int custId);
	public Order getOrder(int orderId);
	public List<Order> getOrders(String custName);
}
