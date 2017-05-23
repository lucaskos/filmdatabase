package com.luke.films.dao;

public class Film {

	private int id;
	private String title;
	private String isbn;
	private Author author;
	
	public Film() {

	}

	public Film(String title, String isbn, Author author) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
	}

	public Film(String title) {
		super();
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + "]";
	}

}
