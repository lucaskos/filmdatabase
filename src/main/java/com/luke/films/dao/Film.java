package com.luke.films.dao;

import javax.validation.constraints.Size;

public class Film {

	private int id;
	
	@Size(max = 60)
	private String title;
	
	private int year;
	
	private String description;
	
	private float rating;
	
	public Film() {

	}

	public Film(String title, int year, String description, float rating) {
		this.title = title;
		this.year = year;
		this.description = description;
		this.rating = rating;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + ", description=" + description + ", rating="
				+ rating + "]";
	}

	

}
