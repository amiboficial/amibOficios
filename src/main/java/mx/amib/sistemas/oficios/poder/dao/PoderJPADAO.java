package mx.amib.sistemas.oficios.poder.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.amib.sistemas.oficios.poder.model.Poder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "poderDAO")
public class PoderJPADAO implements PoderDAO {
	
	private EntityManager em = null;
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional(readOnly = true)
	public Poder get(Long id) {
		return em.createQuery("select p from Poder p where p.id=:id", Poder.class).setParameter("id", id).getSingleResult();
	}
	
}
