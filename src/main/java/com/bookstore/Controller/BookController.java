package com.bookstore.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.CustomException.BookNotFoundException;
import com.bookstore.Entity.Book;
import com.bookstore.Service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookService bookservice;

	@PostMapping("/postbooks")
	public String postbooks(@RequestBody List<Book> books) {
		return bookservice.postbooks(books);
	}

	@PostMapping("/postbook")
	public String postbook(@RequestBody Book book) {
		return bookservice.postbooks(book);
	}

	@GetMapping("/getbook/{id}")
	public Book getBookById(@PathVariable int id) {
		return bookservice.getBookById(id);
	}

	@GetMapping("/getbooks")
	public List<Book> getAllBook() {
		return bookservice.getAllBook();
	}

	@PutMapping("/update/{id}")
	public String updateBook(@PathVariable("id") int id, @RequestBody Book book) {
	    return bookservice.updateBook(id, book);
	}

	@DeleteMapping("/deletebyid/{id}")
	public String deletebook(@PathVariable int id) {
		return bookservice.deletebook(id);
	}

	@PostMapping("/uploadImage/{id}")
	public String uploadImage(@PathVariable int id, @RequestParam("image") MultipartFile file) {
		return bookservice.uploadImage(id, file);
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getBookImage(@PathVariable int id) {
		byte[] imageData = bookservice.getBookImage(id);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG
				.body(imageData);
	}

//	@PostMapping(value = "/postbookwithimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<Map<String,String>>  postBookWithImage(@RequestPart("book") String bookJson,
//			@RequestPart("image") MultipartFile imageFile) {
//		try {
//			
////			System.out.println("📥 Received book JSON: " + bookJson);
////	        System.out.println("📷 Received file: " + imageFile.getOriginalFilename());
//			// convert JSON string to Book object
//			ObjectMapper mapper = new ObjectMapper();
//			Book book = mapper.readValue(bookJson, Book.class);
//			bookservice.saveBookWithImage(book, imageFile);
//
//	        // ✅ Return JSON object instead of plain text
//	        Map<String, String> response = new HashMap<>();
//	        response.put("message", "Book added successfully");
//	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("Failed to parse book JSON", e);
//		}
//	}
//	
//	@GetMapping("/search")
//	public List<Book> searchBooks(@RequestParam("keyword") String keyword) throws BookNotFoundException{
//		return bookservice.searchBooks(keyword);
//	}
	
	@GetMapping("/search")
	public Page<Book> searchBooks(@RequestParam String title,
	                              @RequestParam(defaultValue = "0") int page,
	                              @RequestParam(defaultValue = "8") int size) {
	    return bookservice.searchBooks(title, page, size);
	}
	
	@GetMapping("/page")
	public Page<Book> getpage(@RequestParam (defaultValue = "0")int page,@RequestParam (defaultValue = "8")int size) {
		return bookservice.getpage(page,size);
	}
	
}



