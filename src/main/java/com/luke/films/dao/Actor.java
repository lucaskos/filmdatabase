package com.luke.films.dao;

import java.util.Set;

public class Actor {
	private int id;
	private String name;
	private Set<Film> filmography;
	
	public Actor() {
		
	}

	public Actor(String name, Set<Film> filmography) {
		this.name = name;
		this.filmography = filmography;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Film> getFilmography() {
		return filmography;
	}

	public void setFilmography(Set<Film> filmography) {
		this.filmography = filmography;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", filmography=" + filmography + "]";
	}
	
	
	
}
