package br.com.utfpr.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.utfpr.eventos.models.Favorite;

@Repository
@Transactional
public class FavoriteDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void insert(Favorite favorite){
		manager.persist(favorite);
	}
	
	public int delete(int id, String email){
		return manager
		.createQuery("delete from Favorite f where f.userEmail = :email AND f.idEvent = :idEvent")
		.setParameter("email", email)
		.setParameter("idEvent", id)
		.executeUpdate();
	}

	public List<Favorite> getAllByUserName(String username) {
		return manager
				.createQuery("select f from Favorite f where f.userEmail = :email", Favorite.class)
				.setParameter("email", username)
				.getResultList();
	}
	
	public boolean checkById(int id) {
		return manager
				.createQuery("select count(f) from Favorite f where f.idEvent = :idEvent", Long.class)
				.setParameter("idEvent", id)
				.getSingleResult() == 0 ? false : true;
	}
}
