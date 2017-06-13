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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actorFilm == null) ? 0 : actorFilm.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Actor other = (Actor) obj;
		if (actorFilm == null) {
			if (other.actorFilm != null)
				return false;
		} else if (!actorFilm.equals(other.actorFilm))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
