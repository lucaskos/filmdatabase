package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.dao.Actor;
import com.luke.films.dao.ActorDao;

@Controller
public class ActorController {

	@Autowired
	private ActorDao actorDao;
	
	@RequestMapping(value = "/addactor", method = RequestMethod.POST)
	public String addactor(@ModelAttribute("actor") @Valid Actor actor, BindingResult results){
		Actor tempActor = new Actor();
		actorDao.addActor(tempActor);
		return "filmslist";
		
	}
	
	
}
