package com.luke.films.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.luke.films.common.ControllerConstants;
import com.luke.films.model.actor.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.films.service.ActorService;

@Controller
public class ActorController {

	private static final Logger logger = Logger.getLogger(ActorController.class);

	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "/addactor", method = RequestMethod.GET)
	public String addActor(Model model) {
		model.addAttribute(ControllerConstants.ACTOR, new Person());
		return "addactor";
	}

	@RequestMapping(value = "/"+ ControllerConstants.ACTOR_LIST, method = RequestMethod.GET)
	public String showActorList(Model model) {
		model.addAttribute(ControllerConstants.ACTOR, actorService.getAllActors());
		logger.info("Person list of size " + actorService.getAllActors().size());
		return ControllerConstants.ACTOR_LIST;
	}

	@RequestMapping(value = "/actoradded", method = RequestMethod.POST)
	public String actorAdded(@Valid Person person, BindingResult results) {
		if (results.hasErrors()) {
			return ControllerConstants.ADD_ACTOR;
		}
		actorService.addActor(person);
		return ControllerConstants.REDIRECT + ControllerConstants.ACTOR_LIST;
	}

	/**
	 * Returns a List of Actors that contains specific String.
	 * 
	 * @param name
	 *            - String representation of name
	 * @return List of {@link Person} objects
	 */
	@RequestMapping(value = "/getActors", method = RequestMethod.GET)
	public @ResponseBody List<Person> getActors(@RequestParam String name) {
		List<Person> results;
		List<Person> allPeople = actorService.getAllActors();
		results = extractActor(allPeople, name);
		return results;
	}

	/**
	 * Given list of all actors it iterates through list and returns new list
	 * that contains given string.
	 */
	private List<Person> extractActor(List<Person> listOfPeople, String nameOfActor) {
		List<Person> results = new ArrayList<Person>();
		for (Person person : listOfPeople)
			if (person.getFirstName().toLowerCase().contains(nameOfActor.toLowerCase())) {
				//person.setActorFilms(null);
				results.add(person);
			}
		return results;
	}
}