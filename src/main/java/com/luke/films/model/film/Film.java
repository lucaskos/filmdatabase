package com.luke.films.model.film;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.luke.films.model.ActorFilm;
import com.luke.films.model.actor.Actor;

@Entity
@Table(name = "film")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private int filmId;

	@NotNull
	@Size(max = 60)
	@NotBlank
	private String title;
	@NumberFormat(style = Style.NUMBER)
	private int year;

	private String description;

	private float rating;

	@OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
	private Set<ActorFilm> actorsFilms = new HashSet<ActorFilm>();
	
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

	public void addActor(ActorFilm actorsFilms) {
		this.actorsFilms.add(actorsFilms);
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
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

	public Set<ActorFilm> getActorsFilms() {
		return this.actorsFilms;
	}

	public void setActorsFilms(Set<ActorFilm> actorsFilms) {
		this.actorsFilms = actorsFilms;
	}

	public void addActorsFilms(ActorFilm actorFilms) {
		this.actorsFilms.add(actorFilms);
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", year=" + year + ", description=" + description
				+ ", rating=" + rating + ", actorsFilms=" + actorsFilms + "]";
	}
	


}
