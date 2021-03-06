package com.luke.films.model.actor;

import javax.persistence.*;

import com.luke.films.model.FilmRelation;
import com.luke.films.model.PersonRelation;
import com.luke.films.model.film.Film;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID")
	private int id;
	@NotBlank
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "BORN_DATE")
	private Date bornDate;
	@Column(name = "DIED_DATE")
	private Date diedDate;

	@OneToMany(targetEntity = PersonRelation.class, mappedBy = "personObject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PersonRelation> personRelations;

	public Set<PersonRelation> getPersonRelations() {
		return personRelations;
	}

	public void setPersonRelations(Set<PersonRelation> personRelations) {
		this.personRelations = personRelations;
	}

	@OneToMany(targetEntity = FilmRelation.class, mappedBy = "personRelationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FilmRelation> filmRelations;

    public Set<FilmRelation> getFilmRelations() {
        return filmRelations;
    }

    public void setFilmRelations(Set<FilmRelation> filmRelations) {
        this.filmRelations = filmRelations;
    }

    /** biography */
	@Column(name = "BIOGRAPHY")
	private String bio;
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "actor",cascade = CascadeType.ALL)
	//private Set<Cast> filmography = new HashSet<Cast>();

	public Person() {

	}

	public Person(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

//	public Set<Cast> getActorFilms() {
//		return this.filmography;
//	}
//
//	public void setActorFilms(Set<Cast> actorFilms) {
//		this.filmography = actorFilms;
//	}
//
//	public void addActorsFilms(Cast actorFilms) {
//		this.filmography.add(actorFilms);
//	}



	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public Date getDiedDate() {
		return diedDate;
	}

	public void setDiedDate(Date diedDate) {
		this.diedDate = diedDate;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((filmography == null) ? 0 : filmography.hashCode());
		result = prime * result + id;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
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
		Person other = (Person) obj;
//		if (filmography == null) {
//			if (other.filmography != null)
//				return false;
//		} else if (!filmography.equals(other.filmography))
//			return false;
		if (id != other.id)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		return true;
	}


}
