package br.com.utfpr.eventos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("events")
public class EventController {

	@RequestMapping("/create")
	public String form(){
		return "event/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insert(){
		return "event/list";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAll(){
		return "event/list";
	}
}
