package com.luke.films.model.comment;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Luke on 25.09.2017.
 */
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private int commentId;

    @Column(name = "OWNER_ID")
    private int userId;

    @NotBlank
    @Column(name = "TEXT")
    private String text;

    @Column(name = "PARENT_COMMENT_ID")
    private Integer parentCommentId;

    @Column(name = "FILM_ID")
    private Integer filmId;

    @Column(name = "ACTOR_ID")
    private Integer actorId;
    //private TimeStamp ?

    public Comment() {}

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", parentCommentId=" + parentCommentId +
                ", filmId=" + filmId +
                ", actorId=" + actorId +
                '}';
    }
}
