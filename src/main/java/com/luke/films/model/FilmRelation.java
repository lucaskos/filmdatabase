package com.luke.films.model;

import com.luke.films.model.actor.Person;
import com.luke.films.model.film.Film;

import javax.persistence.*;

/**
 * Created by Luke on 29.10.2017.
 */
@Entity
@Table(name = "FILM_RELATION")
public class FilmRelation {

    @Id
    @Column(name = "ID_FILM_RELATION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int filmRelationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FILM_ID")
    private Film filmId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSON_RELATION_ID")
    private Person personRelationId;

    public int getFilmRelationId() {
        return filmRelationId;
    }

    public void setFilmRelationId(int filmRelationId) {
        this.filmRelationId = filmRelationId;
    }

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public Person getPersonRelationId() {
        return personRelationId;
    }

    public void setPersonRelationId(Person personRelationId) {
        this.personRelationId = personRelationId;
    }
}
