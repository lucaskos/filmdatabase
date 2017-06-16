package com.luke.films.model.actor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "actor",cascade = CascadeType.ALL)
	private Set<Cast> filmography = new HashSet<Cast>();

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
		return this.filmography;
	}

	public void setActorFilms(Set<Cast> actorFilms) {
		this.filmography = actorFilms;
	}

	public void addActorsFilms(Cast actorFilms) {
		this.filmography.add(actorFilms);
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filmography == null) ? 0 : filmography.hashCode());
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
		if (filmography == null) {
			if (other.filmography != null)
				return false;
		} else if (!filmography.equals(other.filmography))
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
