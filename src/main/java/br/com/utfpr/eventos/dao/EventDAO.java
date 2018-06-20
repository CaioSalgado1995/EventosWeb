package br.com.utfpr.eventos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.utfpr.eventos.models.Event;
import br.com.utfpr.eventos.models.Payment;

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

	public List<Event> getEventsPayed(List<Payment> myPayments) {
		List<Integer> ids = new ArrayList<Integer>();
		
		for (Payment payment : myPayments) {
			ids.add(new Integer(payment.getId()));
		}
		
		return manager
				.createQuery("select e from Event e where id IN :ids", Event.class)
				.setParameter("ids", ids)
				.getResultList();
	}
}
