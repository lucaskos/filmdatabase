package com.luke.films.test.controllers;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfigCore.class, HibernateConfig.class})
@ActiveProfiles("test")
@WebAppConfiguration("WebContent")
public class UserControllerTest {

}
