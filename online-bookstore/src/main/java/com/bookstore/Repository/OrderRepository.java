package com.bookstore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.Entity.Order;
import com.bookstore.Entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUserId(int userId);
	List<Order> findByUser(User user);

}
