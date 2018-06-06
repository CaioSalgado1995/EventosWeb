package br.com.utfpr.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.utfpr.eventos.models.User;

@Repository
public class UserDAO implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void insert(User user) {
		manager.persist(user);
	}
	
	public User loadUserByUsername(String email) {
		
		List<User> users =  manager.createQuery("select u from user u where u.email = :email", User.class)
		.setParameter("email", email)
		.getResultList();
		
		if(users.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return users.get(0);
	}
}
