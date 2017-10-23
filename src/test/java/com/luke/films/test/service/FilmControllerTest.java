package com.luke.films.test.service;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.controllers.FilmsController;
import com.luke.films.model.comment.Comment;
import com.luke.films.model.comment.CommentDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Luke on 25.09.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "dev")
@PropertySource("classpath:spring.properties")
public class FilmControllerTest {

    @Autowired
    private FilmsController filmsController;

    @Autowired
    private CommentDao commentDao;

    @Before
    public void initializeMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        List<Comment> list = commentDao.getFilmComments(94);

        System.out.println(list.size());

        for(Comment c : list)
            System.out.println(c);

        List<Comment> commentList = commentDao.getCommentById();

        for(Comment c : commentList)
            System.out.println(c);

        List<Comment> actorComments = commentDao.getActorComments(39);
        System.out.println(actorComments.size());
    }


}
