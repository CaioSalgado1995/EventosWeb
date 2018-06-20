package br.com.utfpr.eventos.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.utfpr.eventos.dao.EventDAO;
import br.com.utfpr.eventos.dao.PaymentsDAO;
import br.com.utfpr.eventos.models.Event;
import br.com.utfpr.eventos.models.Payment;

@Controller
@RequestMapping("/payments")
public class PaymentsController {
	
	@Autowired
	private PaymentsDAO paymentDAO;
	
	@Autowired
	private EventDAO eventDAO;
	
	// TODO inform to this method the user logged
	// TODO restrict this page to users logged
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getAll(Principal principal){
		
		List<Payment> myPayments = paymentDAO.getAllFromUser(principal.getName());
		List<Event> myEventsPayed = eventDAO.getEventsPayed(myPayments);
		
		ModelAndView modelAndView = new ModelAndView("payments/my");
		modelAndView.addObject("myEventsPayed", myEventsPayed);
		
		if(myPayments.size() == 0) {
			modelAndView.addObject("emptySet", true);
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/finish")
	public ModelAndView finishPayment(){
		// TODO
		// Save payment into data base
		// send an email
		// redirect to my payments
		return new ModelAndView();
	}
	
	
}
