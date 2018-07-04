package br.com.utfpr.eventos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.utfpr.eventos.models.Cart;
import br.com.utfpr.eventos.models.Event;

@Repository
@Transactional
public class EventDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void insert(Event event){
		manager.persist(event);
	}
	
	public List<Event> getAll(){
		return manager.createQuery("select e from Event e", Event.class)
				.getResultList();
	}
	
	public Event getById(int id) {
		return manager.createQuery("select e from Event e where e.id = :id", Event.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Event> getEventsOnCart(List<Cart> myCart) {
		List<Integer> ids = new ArrayList<Integer>();
		
		for (Cart cart : myCart) {
			ids.add(new Integer(cart.getIdEvent()));
		}
		
		return manager
				.createQuery("select e from Event e where id IN :ids", Event.class)
				.setParameter("ids", ids)
				.getResultList();
	}
	
	public List<Event> getEventsByName(String name) {
		return manager
				.createQuery("select e from Event e where e.name like :name", Event.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}
}
