package com.bookstore.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.Entity.User;
import com.bookstore.Repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userrepository;
	
	public String registerUser(User user) {
		userrepository.save(user);
		return "User Register SuccessFully";
	}

	public Optional<User> findByEmail(String email) {
		return userrepository.findByEmail(email);
	}
	
	public Optional<User> findById(int id) {
	    return userrepository.findById(id); 
	}
}
