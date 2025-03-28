package com.epam.rd.autotasks.library;

public class Book {
	private String title;
	private Author author;
	private Publisher publisher;
	private String type;


	public Book(String title, Author author, Publisher publisher, String type) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public Author getAuthor() {
		return author;
	}


	public Publisher getPublisher() {
		return publisher;
	}


	public String getType() {
		return type;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setTitle(String title) {
		this.title = title;
	}
}