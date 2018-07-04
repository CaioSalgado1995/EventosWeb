package br.com.utfpr.eventos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.utfpr.eventos.dao.EventDAO;
import br.com.utfpr.eventos.models.Event;
import br.com.utfpr.eventos.models.Search;
import br.com.utfpr.eventos.validation.SearchValidation;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private EventDAO eventDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new SearchValidation());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView search(@Valid Search search, BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("event/list");
		
		if(result.hasErrors()) {
			return new ModelAndView("redirect:events");
		}
		
		List<Event> listEventByName = eventDAO.getEventsByName(search.getSearch());
		
		if(listEventByName.isEmpty()) {
			modelAndView.addObject("emptySet", true);
		}
		
		modelAndView.addObject("events", listEventByName);
		
		return modelAndView;
	}
}
