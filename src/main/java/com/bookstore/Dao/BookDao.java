package com.bookstore.Dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.Entity.Book;
import com.bookstore.Repository.BookRepository;

@Repository
public class BookDao {
	@Autowired
	private BookRepository bookrepository;

	public String postbooks(List<Book> books) {
		bookrepository.saveAll(books);
		return "posted SuccessFull";
	}

	public String postbooks(Book book) {
		bookrepository.save(book);
		return "post successFull";
	}

	public Book getBookById(int id) {
		return bookrepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
	}

	public List<Book> getAllBook() {
		return bookrepository.findAll();
	}

	public String updateBook(int a, Book updatedBook) {
		Book book = bookrepository.findById(a).orElseThrow(() -> new RuntimeException("Book not found"));
		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		book.setPrice(updatedBook.getPrice());
		book.setQuantity(updatedBook.getQuantity());
		return "updated Successfully";
	}

	public String deletebook(int a) {
		bookrepository.deleteById(a);
		return "Deleted Successfully";
	}

	public String uploadImage(int id, MultipartFile file) {
		Book book = bookrepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

		try {
			book.setImage(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to upload image", e);
		}

		bookrepository.save(book);
		return "Image uploaded successfully";
	}

	public byte[] getBookImage(int id) {
		Book book = bookrepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		return book.getImage();
	}

	public List<Book> searchBooks(String keyword) {
		return bookrepository.findByTitleContainingIgnoreCase(keyword);
	}

	public Page<Book> getpage(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
