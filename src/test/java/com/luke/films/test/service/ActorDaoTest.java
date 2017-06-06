package com.luke.films.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.model.actor.Actor;
import com.luke.films.model.actor.ActorDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class ActorDaoTest {
	@Autowired
	private ActorDao actorDao;
	
	@Test
	public void actorTest(){
		List<Actor> allActors = actorDao.getAllActors();
		if(allActors != null){
			for(Actor a : allActors)
				actorDao.deleteActor(a);
		}
		
		assertNull(actorDao.getAllActors());
		
		Actor actor1 = new Actor("Keanu Reeves");
		Actor actor2 = new Actor("Anthony Hopkins");
		
		actorDao.addActor(actor1);
		actorDao.addActor(actor2);
		
		assertNotNull(actorDao.getAllActors());
		
		actorDao.deleteActor(actor1);
		actorDao.deleteActor(actor2);
		
		assertNull(actorDao.getAllActors());
	}
}
