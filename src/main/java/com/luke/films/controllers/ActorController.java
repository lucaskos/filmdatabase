package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = "/actoradded", method=RequestMethod.POST)
	public String actorAdded(@Valid Actor actor, BindingResult results){
		//TODO error handling
		if(results.hasErrors()) {
			return "addactor";
		}
		actorService.addActor(actor);
		return "redirect:/actorlist";
	}
}
