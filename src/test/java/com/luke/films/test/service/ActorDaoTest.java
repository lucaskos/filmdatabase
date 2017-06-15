package com.luke.films.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.model.actor.Actor;
import com.luke.films.service.ActorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class ActorDaoTest {
	@Autowired
	private ActorService actorService;
//TODO learn how  to mockuser
	@Before
	@WithMockUser(username = "admin", authorities = "ROLE_ADMIN", roles="ROLE_ADMIN")
	public void deleteActors() {

		List<Actor> allActors = actorService.getAllActors();
		if (allActors != null) {
			for (Actor a : allActors)
				actorService.deleteActor(a);
		}
		System.out.println(actorService.getAllActors());
	}

	@Test
	@WithMockUser(username = "lucaskos", authorities = "ROLE_USER")
	public void addActor() {
		assertNull(actorService.getAllActors());
		Actor actor1 = new Actor("Keanu Reeves");
		Actor actor2 = new Actor("Anthony Hopkins");

		actorService.addActor(actor1);
		actorService.addActor(actor2);
		assertNotNull(actorService.getAllActors());
		
		String name = "e";
		List<Actor> allActors = actorService.getAllActors();
		List<Actor> containList = new ArrayList<Actor>();
		for (Actor actor : allActors)
			if (actor.getName().contains(name))
				containList.add(actor);
		System.out.println("\n\nContain list : " + containList);
	}

	@After
	@WithMockUser(username = "admin", authorities = "ROLE_ADMIN", roles="ROLE_ADMIN")
	public void deleteAllActors() {
		List<Actor> allActors = actorService.getAllActors();
		for (Actor a : allActors)
			System.out.println(a);
		}
}
