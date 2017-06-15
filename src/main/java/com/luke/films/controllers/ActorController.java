package com.luke.films.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.cast.CastDao;
import com.luke.films.model.cast.CastDaoImpl;
import com.luke.films.service.ActorService;

/*
 * actorList
 * addActor
 *
 */
@Controller
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	@Autowired
	CastDao castDao;

	@RequestMapping(value = "/actorlist", method = RequestMethod.GET)
	public String showActorList(Model model) {
		model.addAttribute("actor", actorService.getAllActors());
		return "actorlist";
	}

	@RequestMapping(value = "/actoradded", method = RequestMethod.POST)
	public String actorAdded(@Valid Actor actor, BindingResult results) {
		// TODO error handling
		if (results.hasErrors()) {
			return "addactor";
		}
		actorService.addActor(actor);
		return "redirect:/actorlist";
	}
	/*
	 * jquery search engine
	 */

	@RequestMapping(value = "/getActors", method = RequestMethod.GET)
	public @ResponseBody List<Actor> getActors(@RequestParam String name) {
			
		List<Actor> results = new ArrayList<Actor>();
		List<Actor> allActors = actorService.getAllActors();
		//TODO extract this to separate method
		for (Actor actor : allActors)
			if (actor.getName().toLowerCase().contains(name.toLowerCase())){
				System.out.println(actor);
				actor.setActorFilms(null);
				results.add(actor);
			}
		return results;
	}
}
