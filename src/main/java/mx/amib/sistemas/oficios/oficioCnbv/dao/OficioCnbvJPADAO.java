package mx.amib.sistemas.oficios.oficioCnbv.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.oficios.oficioCnbv.model.OficioCnbv;
import mx.amib.sistemas.utils.SearchResult;

public class OficioCnbvJPADAO implements OficioCnbvDAO {

	private EntityManager em = null;
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public SearchResult<OficioCnbv> findAll(Integer max, Integer offset,
			String sort, String order) {
		
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		List<OficioCnbv> olist = new ArrayList<OficioCnbv>();
		
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
		else if(!Arrays.asList( new String[]{"id","claveDga","fechaInicioVigencia"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order == ""){
			order = "asc";
		}
		else if(order != "desc" && order != "asc"){
			order = "asc";
		}

		olist = this.em.createQuery("SELECT o FROM OficioCnbv o ORDER BY " + sort + " " + order, OficioCnbv.class)
				.setFirstResult(offset).setMaxResults(max).getResultList();
		rs.setList(olist);
		rs.setCount(
			em.createQuery("SELECT count(r) FROM OficioCnbv o", Long.class).getSingleResult()
		);
		
		return rs;
	}

	public SearchResult<OficioCnbv> findAllBy(Integer max, Integer offset,
			String sort, String order, String claveDga, Integer fechaDelDia,
			Integer fechaDelMes, Integer fechaDelAnio, Integer fechaAlDia,
			Integer fechaAlMes, Integer fechaAlAnio) {
		// TODO Auto-generated method stub
		return null;
	}

	public SearchResult<OficioCnbv> findAllByIdCertificacionInAutorizados(
			Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public SearchResult<OficioCnbv> findAllByMultipleIdCertificacionInAutorizados(
			Set<Long> multipleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public OficioCnbv get(Long id) {
		return this.em.find(OficioCnbv.class, id);
	}

	@Transactional(readOnly = false)
	public OficioCnbv save(OficioCnbv of) {
		this.em.persist(of);
		this.em.flush();
		return of;
	}

	@Transactional(readOnly = false)
	public OficioCnbv update(OficioCnbv of) {
		this.em.merge(of);
		this.em.flush();
		return of;
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		this.em.remove( this.get(id) );
		this.em.flush();
	}

}
