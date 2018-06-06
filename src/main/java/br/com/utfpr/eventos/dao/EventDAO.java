package br.com.utfpr.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.utfpr.eventos.models.Event;

@Repository
@Transactional
public class EventDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Event> getAll(){
		return manager.createQuery("select e from Event e", Event.class).getResultList();
	}
}
