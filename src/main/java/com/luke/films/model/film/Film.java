package com.luke.films.model.film;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.luke.films.model.cast.Cast;
import com.luke.films.model.rating.Rating;

@Entity
@Table(name = "film")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private int filmId;

	@Size(max = 60)
	@NotBlank
	private String title;
	@NotNull
	private Integer year;

	@NotBlank
	@Size(min = 10)
	private String description;

	@OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<Cast> actorsFilms = new HashSet<Cast>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "film", cascade = CascadeType.ALL)
	private Set<Rating> rating = new HashSet<Rating>();

	public Film() {

	}

	public Set<Rating> getRating() {
		return rating;
	}

	public void setRating(Set<Rating> rating) {
		this.rating = rating;
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

	public void addActor(Cast actorsFilms) {
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Cast> getActorsFilms() {
		return this.actorsFilms;
	}

	public void setActorsFilms(Set<Cast> actorsFilms) {
		this.actorsFilms = actorsFilms;
	}

	public void addActorsFilms(Cast actorFilms) {
		this.actorsFilms.add(actorFilms);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + filmId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (filmId != other.filmId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}


}
