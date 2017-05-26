package com.luke.films.dao;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "films")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Size(max = 60)
	@NotBlank
	private String title;
	@NumberFormat(style=Style.NUMBER)
	private int year;

	private String description;

	private float rating;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "actor_films", joinColumns = @JoinColumn(name = "films_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
	private Set<Actor> actors;

	public Film() {

	}

	public Film(String title, int year) {
		this.title = title;
		this.year = year;
	}

	public Film(String title, int year, String description) {
		this.title = title;
		this.year = year;
		this.description = description;
	}

	public Film(String title, int year, String description, Set<Actor> actors) {
		this.title = title;
		this.year = year;
		this.description = description;
		this.actors = actors;
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

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + ", description=" + description + ", rating="
				+ rating + "]";
	}

}
