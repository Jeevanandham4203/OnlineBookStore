package com.bookstore.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByTitleContainingIgnoreCase(String keyword);
	
//	Page<Book> findByTitleContainingIgnoreCase(String title, Pageable keyword);
	
	Page<Book> findAll(Pageable pageable);


	Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);






}
