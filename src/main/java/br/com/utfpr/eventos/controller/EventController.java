package br.com.utfpr.eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.utfpr.eventos.dao.EventDAO;
import br.com.utfpr.eventos.models.Event;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventDAO eventDAO;
	
	@RequestMapping("/create")
	public String form(){
		return "event/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insert(){
		return "event/list";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getAll(){
		List<Event> events = eventDAO.getAll();
		ModelAndView modelAndView = new ModelAndView("event/list");
		modelAndView.addObject("events", events);
		
		if(events.size() == 0) {
			modelAndView.addObject("emptySet", true);
		}
		
		return modelAndView;
	}
}
