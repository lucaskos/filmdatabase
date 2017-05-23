package com.luke.films.dao;

public class Role {
	private Film film;
	private Actor actor;
	private String role;
	
	public Role(){
		
	}

	public Role(Film film, Actor actor, String role) {
		super();
		this.film = film;
		this.actor = actor;
		this.role = role;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
