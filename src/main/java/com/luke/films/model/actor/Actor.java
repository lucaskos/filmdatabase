package com.luke.films.model.actor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.luke.films.model.cast.Cast;

@Entity
@Table(name = "actor")
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actor_id")
	private int id;
	@NotBlank
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
	private Set<Cast> actorFilm = new HashSet<Cast>();

	public Actor() {

	}

	public Actor(String name) {
		this.name = name;
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

	public Set<Cast> getActorFilms() {
		return this.actorFilm;
	}

	public void setActorFilms(Set<Cast> actorFilms) {
		this.actorFilm = actorFilms;
	}

	public void addActorsFilms(Cast actorFilms) {
		this.actorFilm.add(actorFilms);
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + "]";
	}

}
