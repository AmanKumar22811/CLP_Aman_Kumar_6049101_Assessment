package cg.demo.jpa_hibernate_assessment;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import cg.demo.jpa_hibernate_assessment.entity.Order;

public class App {
	static DAOImplementation dao = new DAOImplementation();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		String opt = null;
		do {
			System.out.println("1-ADD, 2--VIEW BY ORDER ID, 3--VIEW BY CUSTOMER NAME");
			int mtype = scan.nextInt();
			processMenu(mtype);
			System.out.println("press y to continue");
			opt=scan.next();
		}while(opt.equalsIgnoreCase("y"));

	}

	public static void processMenu(int mtype) {
		switch(mtype) {
		case 1:
			addOrder();
			break;
		case 2:
			viewOrderByOrderID();
			break;
		case 3:
			viewOrdersByCustName();
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	public static void addOrder() {
		System.out.print("Enter Customer ID: ");
		int custId = scan.nextInt();

		System.out.print("Enter Order Amount: ");
		double amt = scan.nextDouble();

		Order order = new Order(LocalDate.now(), amt);

		boolean status = dao.addOrder(order, custId);

		if (status) {
			System.out.println("Order Added Successfully!");
		} else {
			System.out.println("Customer Not Found with ID: " + custId);
		}		
	}

	public static void viewOrderByOrderID() {
		System.out.print("Enter Order ID: ");
		int orderId = scan.nextInt();

		Order result = dao.getOrder(orderId);

		if (result == null) {
			System.out.println("No Order Found with ID: " + orderId);
		} else {
			System.out.println(
					"Order ID: "  + result.getOrderId()   +
					" Date: "     + result.getOrderDate() +
					" Amount: "   + result.getOrderAmt()  +
					" Customer: " + result.getCustomer().getCustomerName()
					);
		}
	}

	public static void viewOrdersByCustName() {
		System.out.print("Enter Customer Name: ");
		scan.nextLine();                          
		String name = scan.nextLine();

		List<Order> list = dao.getOrders(name);

		if (list.isEmpty()) {
			System.out.println("No Orders Found for Customer: " + name);
		} else {
			for (Order o : list) {
				System.out.println(
						"Order ID: "  + o.getOrderId()   +
						" Date: "     + o.getOrderDate() +
						" Amount: "   + o.getOrderAmt()  +
						" Customer: " + o.getCustomer().getCustomerName()
						);
			}
		}
	}
}