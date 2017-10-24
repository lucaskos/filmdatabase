package com.luke.films.model.comment;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 25.09.2017.
 */
@Component
@Transactional
public class CommentDaoImp implements CommentDao{

    private String query;
    private static int MAIN_COMMENT_DEPTH = 0;
    private static int ONE_ENTITY = 0;

    private static final Logger log = LoggerFactory.getLogger(CommentDaoImp.class);

    private List<Comment> mainComments;

    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> getFilmComments(int filmId) {
        mainComments = new ArrayList<>();
        query = "from Comment where filmId = :id";
        List<Comment> comments = sessionFactory.getCurrentSession().createQuery(query).setParameter("id", filmId).list();
        if(comments != null) {
            for(Comment c : comments) {
                if(c.getParentId() == null) {
                    mainComments.add(c);
                }
            }
        }
        return mainComments;
    }

    public List<Comment> getActorComments(int actorId) {
        mainComments = new ArrayList<>();
        query = "from Comment where actorId = ?1";
        List<Comment> comments = sessionFactory.getCurrentSession().createQuery(query).setParameter("1", actorId).list();
        if(comments != null) {
            for(Comment c : comments) {
                if(c.getParentId() == null) {
                    mainComments.add(c);
                }
            }
        }
        return mainComments;
    }

    public List<Comment> getCommentById() {
        query = "from Comment";
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }

    public Comment getCommentById(int id) {
        query = "from Comment where id=?l";
        return (Comment) sessionFactory.getCurrentSession().createQuery(query).setParameter("l", id).list().get(ONE_ENTITY);
    }

    @Override
    public void insertComment(Comment comment, Film film, Actor actor) {

        if(film != null) {
            comment.setFilmId(film.getFilmId());
        } else if(actor != null) {
            comment.setActorId(actor.getId());
        } else {
            log.info("Neither film nor actor available");
        }

        if(comment != null) {
            comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            sessionFactory.getCurrentSession().save(comment);
        }
    }

    @Override
    public List<Comment> getAllHierarchyComment(int id) {
        query = "from Comment where id = ?commentId";
        sessionFactory.getCurrentSession().createQuery(query).setParameter("commentId", id).list();
        return null;
    }

    @Override
    public List<Comment> getUserComments(String username) {
        return null;
    }
}
