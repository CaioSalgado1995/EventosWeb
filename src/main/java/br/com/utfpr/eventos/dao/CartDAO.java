package br.com.utfpr.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.utfpr.eventos.models.Cart;

@Repository
@Transactional
public class CartDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void insert(Cart cartItem){
		manager.persist(cartItem);
	}
	
	public int remove(int id){
		return manager.createQuery("delete from Cart c where c.id = :id")
		.setParameter("id", id)
		.executeUpdate();
	}

	public List<Cart> getAllFromUser(String email){
		return manager
				.createQuery("select c from Cart c where c.userEmail = :email AND c.status = :status", Cart.class)
				.setParameter("email", email)
				.setParameter("status", "C")
				.getResultList();
	}

	public Cart getByUserAndId(int id, String email) {
		return manager
				.createQuery("select c from Cart c where c.userEmail = :email AND c.idEvent = :idEvent AND c.status = :status", Cart.class)
				.setParameter("email", email)
				.setParameter("idEvent", id)
				.setParameter("status", "C")
				.getResultList().get(0);
	}

	public int updateStatus(int id) {
		return manager
				.createQuery("update Cart c set c.status = :status where c.id = :id")
				.setParameter("status", "P")
				.setParameter("id", id)
				.executeUpdate();
		
	}

	public List<Cart> getByUserAndStatus(String email, String status) {
		return manager
				.createQuery("select c from Cart c where c.userEmail = :email AND c.status = :status", Cart.class)
				.setParameter("email", email)
				.setParameter("status", status)
				.getResultList();
	}
	
}
