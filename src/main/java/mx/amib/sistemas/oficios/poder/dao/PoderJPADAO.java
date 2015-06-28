package mx.amib.sistemas.oficios.poder.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.amib.sistemas.oficios.poder.model.Poder;
import mx.amib.sistemas.utils.SearchResult;

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

	@Transactional(readOnly = true)
	public SearchResult<Poder> findAll(Integer max, Integer offset, String sort,
			String order) {
		SearchResult<Poder> rs = new SearchResult<Poder>();
		
		List<Poder> plist = new ArrayList<Poder>();
		
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
		else if(!Arrays.asList( new String[]{"id","fechaApoderamiento","numeroEscritura"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order == ""){
			order = "asc";
		}
		else if(order != "desc" && order != "asc"){
			order = "asc";
		}
		
		plist = em.createQuery("SELECT p FROM Poder p ORDER BY " + sort + " " + order, Poder.class)
				.setFirstResult(offset).setMaxResults(max).getResultList();
		rs.setList(plist);
		rs.setCount(
			em.createQuery("SELECT count(p) FROM Poder p", Long.class).getSingleResult()
		);
		rs.setError(false);
		
		return rs;
	}

	@Transactional(readOnly = true)
	public SearchResult<Poder> findAllBy(Integer max, Integer offset, String sort, String order, 
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
		String strQlCount = null;
		
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
		else if(!Arrays.asList( new String[]{"id","fechaApoderamiento","numeroEscritura"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order == ""){
			order = "asc";
		}
		else if(order != "desc" && order != "asc"){
			order = "asc";
		}
		//formar fechas
		if( (fechaDelDia != null && fechaDelMes != null && fechaDelAnio != null) && 
			(fechaDelDia > 0 && fechaDelMes > 0 && fechaDelAnio > 0) ){
			fechaDelCalendar = new GregorianCalendar(fechaDelAnio,fechaDelMes-1,fechaDelDia,00,00,00);
		}
		if( (fechaAlDia != null && fechaAlMes != null && fechaAlAnio != null) &&
			(fechaAlDia > 0 && fechaAlMes > 0 && fechaAlAnio > 0) ){
			fechaAlCalendar = new GregorianCalendar(fechaAlDia,fechaAlMes-1,fechaAlAnio,00,00,00);
		}
		
		if(numeroEscritura != null && numeroEscritura != -1){
			filters.add("n.numeroEscritura = :numeroEscritura ");
			whereKeywordNeeded = true;
			namedParameters.put("numeroEscritura",numeroEscritura);
		}
		//rangos de fecha
		//si ambos son nulos, se omite; si uno es nulo, el que no es nulo se toma como unico
		if( fechaDelCalendar != null && fechaAlCalendar == null){
			filters.add("n.fechaApoderamiento >= :fechaApoderamiento ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaApoderamiento",fechaDelCalendar.getTime());
		}
		if( fechaDelCalendar == null && fechaAlCalendar != null){
			filters.add("n.fechaApoderamiento <= :fechaApoderamiento ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaApoderamiento",fechaAlCalendar.getTime());
		}
		if( fechaDelCalendar != null && fechaAlCalendar != null){
			filters.add("n.fechaApoderamiento between :fechaApoderamientoDel and :fechaApoderamientoAl ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaApoderamientoDel",fechaDelCalendar.getTime());
			namedParameters.put("fechaApoderamientoAl",fechaAlCalendar.getTime());
		}
		//idGrupoFinanciero
		if(idGrupoFinanciero != null && idGrupoFinanciero > 0){
			filters.add("n.idGrupoFinanciero = :idGrupoFinanciero ");
			whereKeywordNeeded = true;
			namedParameters.put("idGrupoFinanciero",idGrupoFinanciero);
		}
		//idInstitucion
		if(idInstitucion != null && idInstitucion > 0){
			filters.add("n.idInstitucion = :idInstitucion ");
			whereKeywordNeeded = true;
			namedParameters.put("idInstitucion",idInstitucion);
		}
		
		sbQl.append("from Poder as n ");
		if(whereKeywordNeeded){
			sbQl.append(whereKeyword);
			
			for(String _it : filters){
				if(_it != filters.get(filters.size()-1))
					sbQl.append(_it).append("and ");
				else
					sbQl.append(_it);
			}
			
		}
		strQlCount = "select count(n) " + sbQl.toString();
		sbQl.append("order by n.").append(sort).append(" ").append(order);
				
		SearchResult<Poder> rs = new SearchResult<Poder>();
		TypedQuery<Long> tpqCount = em.createQuery(strQlCount.toString(), Long.class);
		TypedQuery<Poder> tpqRl = em.createQuery(sbQl.toString(), Poder.class);
		for( String key : namedParameters.keySet() ){
			tpqCount.setParameter( key , namedParameters.get(key) );
			tpqRl.setParameter( key , namedParameters.get(key) );
		}
		tpqRl = tpqRl.setFirstResult(offset).setMaxResults(max);
		rs.setCount(tpqCount.getSingleResult());
		rs.setList(tpqRl.getResultList());
		rs.setError(false);

		return rs;
	}

	@Transactional(readOnly = false)
	public Poder save(Poder p) {
		this.em.persist(p);
		this.em.flush();
		return p;
	}

	@Transactional(readOnly = false)
	public Poder update(Poder p) {
		this.em.merge(p);
		this.em.flush();
		return p;
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		this.em.remove( this.get(id) );
		this.em.flush();
	}
	
}
