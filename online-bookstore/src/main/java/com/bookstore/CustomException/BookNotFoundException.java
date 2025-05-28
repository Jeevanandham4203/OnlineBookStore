package com.bookstore.CustomException;

public class BookNotFoundException extends Exception{
	public BookNotFoundException(String msg) {
		super(msg);
	}
}
