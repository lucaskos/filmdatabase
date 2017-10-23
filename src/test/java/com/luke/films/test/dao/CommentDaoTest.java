package com.luke.films.test.dao;

/**
 * Created by Luke on 20.10.2017.
 */

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.ConfigurationConstants;
import com.luke.films.config.HibernateConfig;
import com.luke.films.test.TestConfig;
import com.luke.films.test.service.FilmServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.jboss.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { TestConfig.class, ConfigurationConstants.class, ApplicationConfigCore.class, HibernateConfig.class})
@ActiveProfiles(profiles = "test")
public class CommentDaoTest {

    @Before
    public void setup() {
        Mockito.reset(ApplicationConfigCore.class);
    }

    @Test
    public void commentTest() {
       // System.out.println("test working");
    }

}