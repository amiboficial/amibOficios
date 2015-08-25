package mx.amib.sistemas.oficios.revocacion.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.oficios.revocacion.model.Revocado;

@Repository("revocadoDAO")
public class RevocadoJPADAO implements RevocadoDAO {

	private EntityManager em = null;
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public List<Revocado> getAll(List<Long> ids) {
		return this.em.createQuery("SELECT rd FROM Revocado rd where rd.id IN (:ids)",Revocado.class).setParameter("ids", ids).getResultList();
	}

	public List<Revocado> findAllByIdApoderadoIn(Set<Long> idsApoderado) {
		return this.em.createQuery("SELECT rd FROM Revocado rd where rd.apoderado.id IN (:ids)",Revocado.class).setParameter("ids", idsApoderado).getResultList();
	}

}
