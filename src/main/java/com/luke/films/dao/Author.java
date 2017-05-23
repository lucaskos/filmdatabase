package com.luke.films.dao;

public class Author {
	private String firstName;
	private String secondName;

	public Author() {

	}

	public Author(String name) {
		this.firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}


	
}
