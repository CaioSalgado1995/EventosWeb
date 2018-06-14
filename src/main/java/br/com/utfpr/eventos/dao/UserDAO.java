package br.com.utfpr.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.utfpr.eventos.models.User;

@Repository
@Transactional
public class UserDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void insert(User user) {
		manager.persist(user);
	}
	
	public User findByEmail(String email) {
		List<User> listUser =  manager.createQuery("select u from User u where u.email = :email", User.class)
		.setParameter("email", email).getResultList();
		
		return listUser.size() > 0 ? listUser.get(0) : null;
		
	}
}
