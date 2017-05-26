package com.luke.films.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "actor")
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "actors")
	private Set<Film> filmography;

	public Actor() {

	}

	public Actor(String name) {
		this.name = name;
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
