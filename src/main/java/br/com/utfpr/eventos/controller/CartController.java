package br.com.utfpr.eventos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.utfpr.eventos.dao.CartDAO;
import br.com.utfpr.eventos.dao.EventDAO;
import br.com.utfpr.eventos.models.Cart;
import br.com.utfpr.eventos.models.Event;
import br.com.utfpr.eventos.models.User;

@Controller
@RequestMapping("/cart")
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CartController {

	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private MailSender sender;
	
	@RequestMapping("/insert/{id}")
	public ModelAndView insert(@PathVariable int id, Principal principal){
		
		ModelAndView modelAndView = new ModelAndView("cart/insert");
		Cart cart = new Cart();
		
		cart.setIdEvent(id);
		cart.setUserEmail(principal.getName());
		cart.setStatus("C");
		
		cartDAO.insert(cart);
		
		modelAndView.addObject("message", "Item adicionado com sucesso");
		
		return modelAndView;
	}
	
	@RequestMapping("/remove/{id}")
	public ModelAndView removeItemFromCart(@PathVariable int id, Principal principal) {
		ModelAndView modelAndView = new ModelAndView("cart/remove");
		
		Cart cart = cartDAO.getByUserAndId(id, principal.getName());
		
		if(cart == null) {
			modelAndView.addObject("message", "Item não existe na base de dados");
			return modelAndView;
		}
		
		int result = cartDAO.remove(cart.getId());
		
		if(result != 0) {
			modelAndView.addObject("message", "Item removido com sucesso");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getAll(Principal principal){
		
		ModelAndView modelAndView = new ModelAndView("cart/list");
		double total = 0;
		
		List<Cart> myCart = cartDAO.getAllFromUser(principal.getName());
		
		if(myCart.size() == 0) {
			modelAndView.addObject("emptySet", true);
			return modelAndView;
		}
		
		List<Event> myCartEvent = new ArrayList<Event>();
		
		for (Cart cart : myCart) {
			Event event = eventDAO.getById(cart.getIdEvent());
			myCartEvent.add(event);
		}
		
		for (Event event : myCartEvent) {
			total += event.getPrice().doubleValue();
		}
		
		modelAndView.addObject("myCartEvents", myCartEvent);
		modelAndView.addObject("total", total);
		
		return modelAndView;
	}
	
	@RequestMapping(value="finish", method=RequestMethod.POST)
	public ModelAndView finish(Principal principal){
		ModelAndView modelAndView = new ModelAndView("cart/finish");
		double total = 0;
		
		List<Cart> myCart = cartDAO.getAllFromUser(principal.getName());
			
		List<Event> myCartEvent = eventDAO.getEventsOnCart(myCart);
		
		for (Cart cart : myCart) {
			Event event = eventDAO.getById(cart.getIdEvent());
			myCartEvent.add(event);
		}
		
		for (Event event : myCartEvent) {
			total += event.getPrice().doubleValue();
		}
		
		for(Cart cart : myCart) {
			cartDAO.updateStatus(cart.getId());
		}
		
		modelAndView.addObject("message", "Transação efetuada com sucesso");
		
		enviaConfirmacao(principal.getName(), total);
		
		return modelAndView;
	}
	
	@RequestMapping(value="history", method=RequestMethod.GET)
	public ModelAndView history(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("payment/history");
		List<Cart> myCartPayed = cartDAO.getByUserAndStatus(principal.getName(), "P");
		List<Event> myPayedEvent = new ArrayList<Event>();
		
		if(myCartPayed == null || myCartPayed.isEmpty()) {
			modelAndView.addObject("emptySet", true);
			return modelAndView;
		}
		
		for (Cart cart : myCartPayed) {
			Event event = eventDAO.getById(cart.getIdEvent());
			myPayedEvent.add(event);
		}
		
		modelAndView.addObject("eventsPayed", myPayedEvent);
		
		return modelAndView;
	}
	
	private void enviaConfirmacao(String userEmail, double value){
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setSubject("Comprovante de compra realizado com sucesso!");
		email.setTo(userEmail);
		email.setText("Sua compra foi realizada com sucesso " + userEmail + "! Com valor total de " + value);
		email.setFrom("caiosalgado1995teste@gmail.com");
		
		sender.send(email);
		
	
	}
}
