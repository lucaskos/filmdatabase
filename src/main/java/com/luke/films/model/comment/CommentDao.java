package com.luke.films.model.comment;

import com.luke.films.model.actor.Person;
import com.luke.films.model.film.Film;

import java.util.List;

/**
 * Created by Luke on 21.10.2017.
 */
public interface CommentDao {

    List<Comment> getFilmComments(int id);

    List<Comment> getActorComments(int id);

    Comment getCommentById(int id);

    void insertComment(Comment comment, Film film, Person person);

    List<Comment> getAllHierarchyComment(int id);

    List<Comment> getUserComments(String username);

}
