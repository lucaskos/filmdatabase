package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.model.actor.Actor;
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

	@RequestMapping(value = "/actorlist", method=RequestMethod.GET)
	public String showActorList(Model model) {
		model.addAttribute("actor", actorService.getAllActors());
		return "actorlist";
	}
	
	@RequestMapping(value = "/addactor", method=RequestMethod.GET)
	public String addActor(Model model){
		model.addAttribute("actor", new Actor());
		return "addactor";
	}
	
	@RequestMapping(value = "/actoradded", method=RequestMethod.POST)
	public String actorAdded(@Valid Actor actor){
		//TODO error handling
		actorService.addActor(actor);
		return "actorlist";
	}
	
}
