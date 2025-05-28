package com.bookstore.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.Entity.Order;
import com.bookstore.Entity.User;
import com.bookstore.Repository.OrderRepository;

@Repository
public class OrderDao {
	@Autowired
	private OrderRepository orderrepository;
	
	public String placeorder(Order order) {
		orderrepository.save(order);
		return "orderPlaced SucessFully";
	}
	
	public List<Order> getAllOrders() {
		return orderrepository.findAll();
	}
	
	public Optional<Order> getOrderById(int id) {
		return orderrepository.findById(id);
	}
	
	public List<Order> getOrdersByUserId(int userId) {
		return orderrepository.findByUserId(userId);
	}
	
	
	public List<Order> getOrdersByUser(User user) {
	    return orderrepository.findByUser(user);
	}

	public void deleteOrder(int id) {
		orderrepository.deleteById(id);
	}

}
