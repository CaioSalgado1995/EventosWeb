package br.com.utfpr.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.utfpr.eventos.dao.UserDAO;
import br.com.utfpr.eventos.models.User;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userDao.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não cadastrado, favor se cadastrsar");
		}
		
		return user;
	}
}
