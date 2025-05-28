package com.bookstore.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.CustomException.BookNotFoundException;
import com.bookstore.Dao.BookDao;
import com.bookstore.Entity.Book;

@Service
public class BookService {
	@Autowired
	private BookDao bookdao;

	public String postbooks(List<Book> books) {
		return bookdao.postbooks(books);
	}

	public String postbooks(Book book) {
		return bookdao.postbooks(book);
	}

	public Book getBookById(int id) {
		return bookdao.getBookById(id);
	}

	public List<Book> getAllBook() {
		return bookdao.getAllBook();
	}

	public String updateBook(int a, Book b) {
		return bookdao.updateBook(a, b);
	}

	public String deletebook(int a) {
		return bookdao.deletebook(a);
	}

	public String uploadImage(int id, MultipartFile file) {
		return bookdao.uploadImage(id, file);
	}

	public byte[] getBookImage(int id) {
		return bookdao.getBookImage(id);
	}

	public String saveBookWithImage(Book book, MultipartFile imageFile) {
		try {
			book.setImage(imageFile.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to read image file", e);
		}
		return bookdao.postbooks(book); // use your existing save logic
	}

	public List<Book> searchBooks(String keyword) throws BookNotFoundException{
		List<Book> books = bookdao.searchBooks(keyword);
	    if (books.isEmpty()) {
	        throw new BookNotFoundException("Book not available with name: " + keyword);
	    }
	    return books;
	}

}
