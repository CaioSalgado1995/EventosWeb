package br.com.utfpr.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.utfpr.eventos.models.Payment;

@Repository
@Transactional
public class PaymentsDAO {

	@PersistenceContext
	private EntityManager manager;

	public void insert(Payment payment){
		manager.persist(payment);
	}

	public List<Payment> getAllFromUser(String email){
		return manager
				.createQuery("select p from Payment p where p.userEmail = :email", Payment.class)
				.setParameter("email", email)
				.getResultList();
	}

}
