package mx.amib.sistemas.oficios.poder.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.oficios.poder.model.Apoderado;

@Repository("apoderadoDAO")
public class ApoderadoJPADAO implements ApoderadoDAO {

	private EntityManager em = null;
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional(readOnly = true)
	public Apoderado get(Long id) {
		Apoderado ap = this.em.find(Apoderado.class, id);
		return ap;
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		Apoderado ap = this.get(id);
		this.em.remove(ap);
	}

	@Transactional(readOnly = false)
	public Apoderado save(Apoderado ap) {
		em.persist(ap);
		em.flush();
		return ap;
	}

	@Transactional(readOnly = false)
	public Apoderado update(Apoderado ap) {
		em.merge(ap);
		em.flush();
		return ap;
	}

}
