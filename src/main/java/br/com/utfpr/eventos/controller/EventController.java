package br.com.utfpr.eventos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.utfpr.eventos.dao.CartDAO;
import br.com.utfpr.eventos.dao.EventDAO;
import br.com.utfpr.eventos.dao.FavoriteDAO;
import br.com.utfpr.eventos.infra.FileSaver;
import br.com.utfpr.eventos.models.Cart;
import br.com.utfpr.eventos.models.Event;
import br.com.utfpr.eventos.models.Search;
import br.com.utfpr.eventos.validation.EventValidation;
import br.com.utfpr.eventos.validation.SearchValidation;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private FavoriteDAO favoriteDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new EventValidation());
	}
	
	@RequestMapping("/create")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("event/form");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insert(MultipartFile image, @Valid Event event, BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()) {
			return form();
		}
		
		String path = fileSaver.write("arquivos-imagem", image);
		event.setImagem(path);
		
		eventDAO.insert(event);
		
		return new ModelAndView("redirect:events");
	}
	
	@RequestMapping(value="remove/{id}")
	public ModelAndView remove(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("event/remove");
		
		List<Cart> cartEvents = cartDAO.getById(id);
		
		if(cartEvents.isEmpty()) {
			eventDAO.delete(id);
			modelAndView.addObject("message", "Evento excluido com sucesso");
			return modelAndView;
		}
		
		modelAndView.addObject("message", "Evento não pode ser excluído pois pertence a lista de compras de algum usuário");
		
		return modelAndView;
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
	
	@RequestMapping("detail/{id}")
	public ModelAndView detail(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView("event/detail");
		Event event = eventDAO.getById(id);
	
		boolean isFavorite = favoriteDAO.checkById(id);
		modelAndView.addObject("isFavorite", isFavorite);
		
		modelAndView.addObject("event", event);
		
		return modelAndView;
	}
}
