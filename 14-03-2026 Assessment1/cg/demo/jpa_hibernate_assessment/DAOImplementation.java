package cg.demo.jpa_hibernate_assessment;

import jakarta.persistence.*;
import java.util.List;

import cg.demo.jpa_hibernate_assessment.entity.Order;
import cg.demo.jpa_hibernate_assessment.entity.Customer;

import cg.demo.jpa_hibernate_assessment.interfaces.IorderDAO;

public class DAOImplementation implements IorderDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public boolean addOrder(Order order, int custId) {

        Customer customer = em.find(Customer.class, custId);
        if (customer == null) {
            return false;
        }
        order.setCustomer(customer);
        customer.getOrders().add(order);

        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();

        return true;
    }   
    
    @Override
    public Order getOrder(int orderId) {
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.order_id = :id", Order.class)
                .setParameter("id", orderId).getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Order> getOrders(String custName) {
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.customer.customer_name = :name", Order.class)
                .setParameter("name", custName).getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}