package cg.demo.jpa_hibernate_assessment.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "abes_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private LocalDate order_date;

    private double order_amt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    
    private Customer customer;

    public Order() {};
    public Order(LocalDate order_date, double order_amt) {
		this.order_date = order_date;
		this.order_amt = order_amt;
	}

	public int getOrderId() {
        return order_id;
    }

    public LocalDate getOrderDate() {
        return order_date;
    }

    public void setOrderDate(LocalDate order_date) {
        this.order_date = order_date;
    }

    public double getOrderAmt() {
        return order_amt;
    }

    public void setOrderAmt(double order_amt) {
        this.order_amt = order_amt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}