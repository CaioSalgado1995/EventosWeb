package br.com.utfpr.eventos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.utfpr.eventos.dao.UserDAO;
import br.com.utfpr.eventos.models.User;
import br.com.utfpr.eventos.validation.UserValidation;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MailSender sender;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidation());
	}
	
	@RequestMapping("/create")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("cadastro/form");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()){
			return register();
		}
		
		userDAO.insert(user);
//		enviaEmailCadastro(user);
		return new ModelAndView("redirect:login");
	}
	
	private void enviaEmailCadastro(User user){
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setSubject("Cadastro de usuário");
		email.setTo(user.getEmail());
		email.setText("Bem vindo " + user.getName() + "! Seu cadastro foi realizado com sucesso");
		email.setFrom("caiosalgado1995teste@gmail.com");
		
		sender.send(email);
		
	
	}
}
