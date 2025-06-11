package com.bookstore.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Dao.OrderDao;
import com.bookstore.Dao.UserDao;
import com.bookstore.Entity.Order;
import com.bookstore.Entity.OrderItem;
import com.bookstore.Entity.User;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderdao;
	
	@Autowired
	private UserDao userDAO;
	
	public String placeorder(Order order) {
		order.setOrderDate(LocalDate.now());
		double total = 0;
	    for (OrderItem item : order.getOrderItems()) {
	        item.setOrder(order); 
	        total += item.getPrice();
	    }
	    order.setTotalAmount(total);
		return orderdao.placeorder(order);
	}

	public List<Order> getAllOrders() {
		return orderdao.getAllOrders();
	}

	public Order getOrderById(int id) {
		return orderdao.
				getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
	}
	
	public List<Order> getOrdersByUserId(int userId) {
	    User user = userDAO.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	    return orderdao.getOrdersByUser(user);
	}
	
	public void deleteOrder(int id) {
		orderdao.deleteOrder(id);
	}

	

}
