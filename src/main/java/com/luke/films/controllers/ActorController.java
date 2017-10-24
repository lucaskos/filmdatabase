package com.luke.films.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.luke.films.common.ControllerConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.films.model.actor.Actor;
import com.luke.films.service.ActorService;

@Controller
public class ActorController {

	private static final Logger logger = Logger.getLogger(ActorController.class);

	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "/addactor", method = RequestMethod.GET)
	public String addActor(Model model) {
		model.addAttribute(ControllerConstants.ACTOR, new Actor());
		return "addactor";
	}

	@RequestMapping(value = "/"+ ControllerConstants.ACTOR_LIST, method = RequestMethod.GET)
	public String showActorList(Model model) {
		model.addAttribute(ControllerConstants.ACTOR, actorService.getAllActors());
		logger.info("Actor list of size " + actorService.getAllActors().size());
		return ControllerConstants.ACTOR_LIST;
	}

	@RequestMapping(value = "/actoradded", method = RequestMethod.POST)
	public String actorAdded(@Valid Actor actor, BindingResult results) {
		if (results.hasErrors()) {
			return ControllerConstants.ADD_ACTOR;
		}
		actorService.addActor(actor);
		return ControllerConstants.REDIRECT + ControllerConstants.ACTOR_LIST;
	}

	/**
	 * Returns a List of Actors that contains specific String.
	 * 
	 * @param name
	 *            - String representation of name
	 * @return List of {@link Actor} objects
	 */
	@RequestMapping(value = "/getActors", method = RequestMethod.GET)
	public @ResponseBody List<Actor> getActors(@RequestParam String name) {
		List<Actor> results;
		List<Actor> allActors = actorService.getAllActors();
		results = extractActor(allActors, name);
		return results;
	}

	/**
	 * Given list of all actors it iterates through list and returns new list
	 * that contains given string.
	 */
	private List<Actor> extractActor(List<Actor> listOfActors, String nameOfActor) {
		List<Actor> results = new ArrayList<Actor>();
		for (Actor actor : listOfActors)
			if (actor.getName().toLowerCase().contains(nameOfActor.toLowerCase())) {
				actor.setActorFilms(null);
				results.add(actor);
			}
		return results;
	}
}