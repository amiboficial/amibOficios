package mx.amib.sistemas.oficios.revocacion.dao;

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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.oficios.revocacion.model.Revocacion;
import mx.amib.sistemas.utils.SearchResult;

@Repository("revocacionDAO")
public class RevocacionJPADAO implements RevocacionDAO {

	private EntityManager em = null;
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public SearchResult<Revocacion> findAll(Integer max, Integer offset,
			String sort, String order) {
		SearchResult<Revocacion> rs = new SearchResult<Revocacion>();
		List<Revocacion> rlist = new ArrayList<Revocacion>();
		
		//Se checan los parametros de "control"
		if(max == null || max <= 0){
			max = 10;
		}
		if(offset == null || offset <= 0){
			offset = 0;
		}
		if(sort == null || sort.trim().compareTo("") == 0 ){
			sort = "id";
		}
		else if(!Arrays.asList( new String[]{"id","fechaRevocacion","numeroEscritura"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order.trim().compareTo("") == 0){
			order = "asc";
		}
		else if(order.compareToIgnoreCase("desc") != 0 && order.compareToIgnoreCase("asc") != 0){
			order = "asc";
		}
		
		rlist = em.createQuery("SELECT r FROM Revocacion r ORDER BY " + sort + " " + order, Revocacion.class)
				.setFirstResult(offset).setMaxResults(max).getResultList();
		rs.setList(rlist);
		rs.setCount(
			em.createQuery("SELECT count(r) FROM Revocacion r", Long.class).getSingleResult()
		);
		rs.setError(false);
		
		return rs;
	}

	public SearchResult<Revocacion> findAllBy(Integer max, Integer offset,
			String sort, String order, Integer numeroEscritura,
			Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,
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
		if(sort == null || sort.trim().compareTo("") == 0 ){
			sort = "id";
		}
		else if(!Arrays.asList( new String[]{"id","fechaRevocacion","numeroEscritura"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order.trim().compareTo("") == 0){
			order = "asc";
		}
		else if(order.compareToIgnoreCase("desc") != 0 && order.compareToIgnoreCase("asc") != 0){
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
			filters.add("n.fechaRevocacion >= :fechaRevocacion ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaRevocacion",fechaDelCalendar.getTime());
		}
		if( fechaDelCalendar == null && fechaAlCalendar != null){
			filters.add("n.fechaRevocacion <= :fechaRevocacion ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaRevocacion",fechaAlCalendar.getTime());
		}
		if( fechaDelCalendar != null && fechaAlCalendar != null){
			filters.add("n.fechaRevocacion between :fechaRevocacionDel and :fechaRevocacionAl ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaRevocacionDel",fechaDelCalendar.getTime());
			namedParameters.put("fechaRevocacionAl",fechaAlCalendar.getTime());
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
		
		sbQl.append("from Revocacion as n ");
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
				
		SearchResult<Revocacion> rs = new SearchResult<Revocacion>();
		TypedQuery<Long> tpqCount = em.createQuery(strQlCount.toString(), Long.class);
		TypedQuery<Revocacion> tpqRl = em.createQuery(sbQl.toString(), Revocacion.class);
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

	@Transactional(readOnly = true)
	public Revocacion get(Long id) {
		Revocacion r = this.em.find(Revocacion.class, id);
		return r;
	}

	@Transactional(readOnly = false)
	public Revocacion save(Revocacion r) {
		this.em.persist(r);
		this.em.flush();
		return r;
	}

	@Transactional(readOnly = false)
	public Revocacion update(Revocacion r) {
		this.em.merge(r);
		this.em.flush();
		return r;
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		this.em.remove( this.get(id) );
		this.em.flush();
	}

	@Transactional(readOnly = true)
	public boolean isNumeroEscrituraAvailable(Integer numeroEscritura) {
		String jpql = "select count(rv.id) from Revocacion as rv where rv.numeroEscritura = :numeroEscritura";
		Long count = em.createQuery(jpql, Long.class).setParameter("numeroEscritura", numeroEscritura).getSingleResult();
		
		if(count > 0)
			return false;
		else
			return true;
	}

}
