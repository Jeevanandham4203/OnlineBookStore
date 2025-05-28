package com.bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Dao.UserDao;
import com.bookstore.Entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;

	public String registerUser(User user) {
		return userdao.registerUser(user);
	}
	
	public User loginUser(String email, String password) {
		return userdao.findByEmail(email)
				.filter(user -> user.getPassword().equals(password))
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));
		
	}

}
