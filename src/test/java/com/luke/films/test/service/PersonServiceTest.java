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
import com.luke.films.model.actor.Person;
import com.luke.films.service.ActorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class PersonServiceTest {
	@Autowired
	private ActorService actorService;
//TODO learn how  to mockuser
	@Before
	@WithMockUser(username = "admin", authorities = "ROLE_ADMIN", roles="ROLE_ADMIN")
	public void deleteActors() {

		List<Person> allPeople = actorService.getAllActors();
		if (allPeople != null) {
			for (Person a : allPeople)
				actorService.deleteActor(a);
		}
		System.out.println(actorService.getAllActors());
	}

	@Test
	@WithMockUser(username = "lucaskos", authorities = "ROLE_USER")
	public void addActor() {
		assertNull(actorService.getAllActors());
		Person person1 = new Person("Keanu Reeves");
		Person person2 = new Person("Anthony Hopkins");

		actorService.addActor(person1);
		actorService.addActor(person2);
		assertNotNull(actorService.getAllActors());
		
		String name = "e";
		List<Person> allPeople = actorService.getAllActors();
		List<Person> containList = new ArrayList<Person>();
		for (Person person : allPeople)
			if (person.getFirstName().contains(name))
				containList.add(person);
		System.out.println("\n\nContain list : " + containList);
	}

	@After
	@WithMockUser(username = "admin", authorities = "ROLE_ADMIN", roles="ROLE_ADMIN")
	public void deleteAllActors() {
		List<Person> allPeople = actorService.getAllActors();
		for (Person a : allPeople)
			System.out.println(a);
		}
}
