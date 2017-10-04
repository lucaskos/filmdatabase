package com.luke.films.model.comment;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Luke on 25.09.2017.
 */
@Component
@Transactional
public class CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> getFilmsComments(int filmId) {
        String query = "from Comment where filmId = ?1";
        List<Comment> list = sessionFactory.getCurrentSession().createQuery(query).setParameter("1", filmId).list();
        return list;
    }

    public List<Comment> getActorComments(int actorId) {
        String query = "from Comment where actorId = ?1";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("1", actorId).list();
    }

    public List<Comment> getCommentById() {
        String query = "from Comment";
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }

}
