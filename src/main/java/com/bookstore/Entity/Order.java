package com.bookstore.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 private LocalDate orderDate;
	 
	 private Double totalAmount;
	 
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name = "user_id", nullable = false)
	 private User user;
	 
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	 @JsonManagedReference
	 private List<OrderItem> orderItems = new ArrayList<>();
}
