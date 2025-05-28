package com.bookstore.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "user_details")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Column(unique = true)
	@Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
	
	@Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

}
