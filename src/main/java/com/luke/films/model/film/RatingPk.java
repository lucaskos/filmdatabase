package com.luke.films.model.film;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.luke.user.model.User;

@Embeddable
public class RatingPk implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -7775591323397555632L;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="film_id", referencedColumnName = "film_id")
	private Film film;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="users_id", referencedColumnName = "id")
	private User user;

	public RatingPk() {

	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		RatingPk other = (RatingPk) obj;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}