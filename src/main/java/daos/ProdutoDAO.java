package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Products;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Products product) {
		manager.persist(product);
	}

	public List<Products> listar() {
		return manager.createQuery("select p from Products p", Products.class).getResultList();
	}

	public Products find(Long id) {
		return manager.createQuery("select distinct(p) from Products p join fetch p.listPreco precos where p.id = :id", 
				Products.class).setParameter("id", id).getSingleResult();
	}

}
