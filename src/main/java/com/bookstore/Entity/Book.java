package com.bookstore.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
@Entity
@Table(name="Book_details")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@NotBlank(message = "Title is required")
    private String title;
	
	@NotBlank(message  = "Author is required")
    private String author;
	
	@Positive(message = "Price must be greater than 0")
    private Double price;
    private int quantity;
    
    @Lob
    @Column(name = "book_image", columnDefinition = "LONGBLOB")
    private byte[] image;
    
    
}
