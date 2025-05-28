package com.bookstore.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByTitleContainingIgnoreCase(String keyword);

}
