package com.bookstore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.Entity.Order;
import com.bookstore.Service.OrderService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderservice;
	
	@PostMapping("/placeorder")
	public String placeorder(@RequestBody Order order) {
		return orderservice.placeorder(order);
	}
	
	@GetMapping("/getorders")
    public List<Order> getAllOrders() {
        return orderservice.getAllOrders();
    }
	
	@GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderservice.getOrderById(id);
    }
	
	@GetMapping("/user/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable int userId) {
	    return orderservice.getOrdersByUserId(userId);
	}

	
	@DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderservice.deleteOrder(id);
        return "Order deleted successfully";
    }
}
