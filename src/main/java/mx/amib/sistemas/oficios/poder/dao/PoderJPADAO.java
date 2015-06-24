package mx.amib.sistemas.oficios.poder.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.amib.sistemas.oficios.poder.model.Poder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("poderDAO")
public class PoderJPADAO implements PoderDAO {
	
	private EntityManager em = null;
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional(readOnly = true)
	public Poder get(Long id) {
		Poder p = em.createQuery("SELECT p FROM Poder p WHERE p.id=:id", Poder.class).setParameter("id", id).getSingleResult();
		return p;
	}

	public List<Poder> findAll(Integer max, Integer offset, String sort,
			String order) {
		List<Poder> plist = em.createQuery("SELECT p FROM Poder p ORDER BY :sort :order ", Poder.class)
						.setFirstResult(offset).setMaxResults(max)
						.setParameter("sort", sort).setParameter("order", order).getResultList();
		return plist;
	}

	public List<Poder> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion) {
		
		Calendar fechaDelCalendar = null;
		Calendar fechaAlCalendar = null;
		List<String> filters = new ArrayList<String>();
		String whereKeyword = "where ";
		Boolean whereKeywordNeeded = false;
		StringBuilder sbQl = new StringBuilder();
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		
		//Se checan los parametros de "control"
		if(max == null || max <= 0){
			max = 10;
		}
		if(offset == null || offset <= 0){
			offset = 0;
		}
		if(sort == null || sort == ""){
			sort = "id";
		}
		/*else if(["id","fechaApoderamiento","numeroEscritura"].find{ sort == it } == null){
			sort = "id";
		}*/
		if(order == null || order == ""){
			order = "asc";
		}
		else if(order != "desc" && order != "asc"){
			order = "asc";
		}
		
		return null;
	}

	public Poder save(Poder s) {
		// TODO Auto-generated method stub
		return null;
	}

	public Poder update(Poder s) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Poder> findAllBy(Integer max, Integer offset, String sort,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
