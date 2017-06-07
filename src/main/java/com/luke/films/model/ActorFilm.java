package com.luke.films.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

@Entity
@Table(name = "actor_film")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ActorFilm implements Serializable {
	@Column(name = "actor_film_id")
	@Id
	@GeneratedValue
	private int actorFilmId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "film_id")
	private Film film;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actor_id")
	private Actor actor;

	@Column(name = "role")
	private String role;

	public ActorFilm() {

	}
	
	public ActorFilm(Film f, Actor a) {
		this.film = f;
		this.actor = a;
	}

	public int getActorFilmId() {
		return actorFilmId;
	}

	public void setActorFilmId(int actorFilmId) {
		this.actorFilmId = actorFilmId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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

}
