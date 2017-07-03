package com.luke.films.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "/actorlist", method = RequestMethod.GET)
	public String showActorList(Model model) {
		model.addAttribute("actor", actorService.getAllActors());
		return "actorlist";
	}

	@RequestMapping(value = "/actoradded", method = RequestMethod.POST)
	public String actorAdded(@Valid Actor actor, BindingResult results) {
		if (results.hasErrors()) {
			return "addactor";
		}
		actorService.addActor(actor);
		return "redirect:/actorlist";
	}

	/**
	 * Returns a List of Actors that contains specific String.
	 * @param name - String representation of name
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