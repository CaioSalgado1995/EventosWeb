package br.com.utfpr.eventos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.utfpr.eventos.dao.EventDAO;
import br.com.utfpr.eventos.dao.FavoriteDAO;
import br.com.utfpr.eventos.models.Event;
import br.com.utfpr.eventos.models.Favorite;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

	@Autowired
	private FavoriteDAO favoriteDAO;
	
	@Autowired
	private EventDAO eventDAO;
	
	
	// method to insert favorite to a user
	@RequestMapping("{id}")
	public ModelAndView insert(@PathVariable int id, Principal principal){
		Favorite favorite = new Favorite();
		favorite.setIdEvent(id);
		favorite.setUserEmail(principal.getName());
		
		
		
		favoriteDAO.insert(favorite);
	
		return getAll(principal);
	}


	@RequestMapping(method=RequestMethod.GET)
	private ModelAndView getAll(Principal principal) {
		String username = principal.getName();
		ModelAndView modelAndView = new ModelAndView("favorite/list");
		List<Favorite> favorites = favoriteDAO.getAllByUserName(username);
		List<Event> events = new ArrayList<Event>();
		
		if(favorites.isEmpty()){
			modelAndView.addObject("emptySet", true);
			return modelAndView;
		}
		
		for (Favorite favorite : favorites) {
			Event event = eventDAO.getById(favorite.getId());
			events.add(event);
		}
		
		modelAndView.addObject("events", events);
		
		return modelAndView;
	}
	
	
}
