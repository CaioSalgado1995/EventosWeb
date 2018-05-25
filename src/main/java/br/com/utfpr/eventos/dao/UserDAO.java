package br.com.utfpr.eventos.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.utfpr.eventos.models.User;

@Repository
@Transactional
public class UserDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void insert(User user) {
		manager.persist(user);
	}
}
