package mx.amib.sistemas.oficios.oficioCnbv.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.oficios.oficioCnbv.model.OficioCnbv;
import mx.amib.sistemas.utils.SearchResult;

@Repository("oficioCnbvDAO")
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
		if(sort == null || sort.trim().compareTo("") == 0 ){
			sort = "id";
		}
		else if(!Arrays.asList( new String[]{"id","numeroOficio","fechaOficio","claveDga"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order.trim().compareTo("") == 0){
			order = "asc";
		}
		else if(order.compareToIgnoreCase("desc") != 0 && order.compareToIgnoreCase("asc") != 0){
			order = "asc";
		}

		olist = this.em.createQuery("SELECT o FROM OficioCnbv o ORDER BY " + sort + " " + order, OficioCnbv.class)
				.setFirstResult(offset).setMaxResults(max).getResultList();
		rs.setList(olist);
		rs.setCount(
			em.createQuery("SELECT count(o) FROM OficioCnbv o", Long.class).getSingleResult()
		);
		
		return rs;
	}

	public SearchResult<OficioCnbv> findAllBy(Integer max, Integer offset,
			String sort, String order, String claveDga, Integer fechaDelDia,
			Integer fechaDelMes, Integer fechaDelAnio, Integer fechaAlDia,
			Integer fechaAlMes, Integer fechaAlAnio) {
		
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
		else if(!Arrays.asList( new String[]{"id","numeroOficio","fechaOficio","claveDga"} ).contains(sort)){
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
		
		//rangos de fecha
		//si ambos son nulos, se omite; si uno es nulo, el que no es nulo se toma como unico
		if( fechaDelCalendar != null && fechaAlCalendar == null){
			filters.add("o.fechaOficio >= :fechaOficio ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaOficio",fechaDelCalendar.getTime());
		}
		if( fechaDelCalendar == null && fechaAlCalendar != null){
			filters.add("o.fechaOficio <= :fechaOficio ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaOficio",fechaAlCalendar.getTime());
		}
		if( fechaDelCalendar != null && fechaAlCalendar != null){
			filters.add("o.fechaOficio between :fechaOficioDel and :fechaOficioAl ");
			whereKeywordNeeded = true;
			namedParameters.put("fechaOficioDel",fechaDelCalendar.getTime());
			namedParameters.put("fechaOficioAl",fechaAlCalendar.getTime());
		}
		
		if(claveDga != null && claveDga != "" ){
			filters.add("o.claveDga = :claveDga ");
			whereKeywordNeeded = true;
			namedParameters.put("claveDga",claveDga);
		}
		
		sbQl.append("from OficioCnbv as o ");
		if(whereKeywordNeeded){
			sbQl.append(whereKeyword);
			for(String _it : filters){
				if(_it != filters.get(filters.size()-1))
					sbQl.append(_it).append("and ");
				else
					sbQl.append(_it);
			}
		}
		
		strQlCount = "select count(o) " + sbQl.toString();
		sbQl.append("order by o.").append(sort).append(" ").append(order);
				
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		TypedQuery<Long> tpqCount = em.createQuery(strQlCount.toString(), Long.class);
		TypedQuery<OficioCnbv> tpqRl = em.createQuery(sbQl.toString(), OficioCnbv.class);
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

	public SearchResult<OficioCnbv> findAllByIdCertificacionInAutorizados( Integer max, Integer offset, String sort, String order, 
			Long id) {
		
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		List<OficioCnbv> olist = new ArrayList<OficioCnbv>();
		
		//Se checan los parametros de "control"
		if(max == null || max <= 0){
			max = 10;
		}
		if(offset == null || offset <= 0){
			offset = 0;
		}
		if(sort == null || sort.trim().compareTo("") == 0 || sort.compareToIgnoreCase("id") == 0 ){
			sort = "o.id";
		}
		else if(!Arrays.asList( new String[]{"id","numeroOficio","fechaOficio","claveDga"} ).contains(sort)){
			sort = "o.id";
		}
		if(order == null || order.trim().compareTo("") == 0){
			order = "asc";
		}
		else if(order.compareToIgnoreCase("desc") != 0 && order.compareToIgnoreCase("asc") != 0){
			order = "asc";
		}

		olist = this.em.createQuery("SELECT o FROM OficioCnbv o JOIN o.autorizados au WHERE au.idCertificacion = :id ORDER BY " + sort + " " + order, OficioCnbv.class)
				.setParameter("id", id).setFirstResult(offset).setMaxResults(max).getResultList();
		rs.setList(olist);
		rs.setCount(
			em.createQuery("SELECT count(o) FROM OficioCnbv o JOIN o.autorizados au WHERE au.idCertificacion = :id", Long.class).setParameter("id", id).getSingleResult()
		);
		
		return rs;
	}

	public SearchResult<OficioCnbv> findAllByMultipleIdCertificacionInAutorizados(Integer max, Integer offset, String sort, String order, 
			Set<Long> multipleIds) {
		
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		List<OficioCnbv> olist = new ArrayList<OficioCnbv>();
		
		//Se checan los parametros de "control"
		if(max == null || max <= 0){
			max = 10;
		}
		if(offset == null || offset <= 0){
			offset = 0;
		}
		if(sort == null || sort.trim().compareTo("") == 0  || sort.compareToIgnoreCase("id") == 0  ){
			sort = "o.id";
		}
		else if(!Arrays.asList( new String[]{"id","numeroOficio","fechaOficio","claveDga"} ).contains(sort)){
			sort = "o.id";
		}
		if(order == null || order.trim().compareTo("") == 0){
			order = "asc";
		}
		else if(order.compareToIgnoreCase("desc") != 0 && order.compareToIgnoreCase("asc") != 0){
			order = "asc";
		}

		olist = this.em.createQuery("SELECT o FROM OficioCnbv o JOIN o.autorizados au WHERE au.idCertificacion IN (:ids) ORDER BY " + sort + " " + order, OficioCnbv.class)
				.setParameter("ids", multipleIds).setFirstResult(offset).setMaxResults(max).getResultList();
		rs.setList(olist);
		rs.setCount(
			em.createQuery("SELECT count(o) FROM OficioCnbv o JOIN o.autorizados au WHERE au.idCertificacion IN (:ids)", Long.class)
			.setParameter("ids", multipleIds).getSingleResult()
		);
		return rs;
	}

	public SearchResult<OficioCnbv> findAllByNumeroOficio(Integer numeroOficio) {
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		List<OficioCnbv> olist = new ArrayList<OficioCnbv>();
		
		olist = em.createQuery("select o from OficioCnbv as o where o.numeroOficio = :numeroOficio",OficioCnbv.class)
					.setParameter("numeroOficio", numeroOficio).getResultList();
		
		rs.setCount((long)olist.size());
		rs.setList(olist);
		rs.setError(false);
		
		return rs;
	}

	public SearchResult<OficioCnbv> findAllByClaveDga(String claveDga) {
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		List<OficioCnbv> olist = new ArrayList<OficioCnbv>();
		
		olist = em.createQuery("select o from OficioCnbv as o where o.claveDga = :claveDga",OficioCnbv.class)
					.setParameter("claveDga", claveDga).getResultList();
		
		rs.setCount((long)olist.size());
		rs.setList(olist);
		rs.setError(false);
		
		return rs;
	}

	public SearchResult<OficioCnbv> findAllByFechaOficio(Integer max, Integer offset, String sort, String order,
			Date fechaOficioDel, Date fechaOficioAl) {
		
		Calendar fechaDelCalendar = Calendar.getInstance();
		Calendar fechaAlCalendar = Calendar.getInstance();
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
		else if(!Arrays.asList( new String[]{"id","numeroOficio","fechaOficio","claveDga"} ).contains(sort)){
			sort = "id";
		}
		if(order == null || order.trim().compareTo("") == 0){
			order = "asc";
		}
		else if(order.compareToIgnoreCase("desc") != 0 && order.compareToIgnoreCase("asc") != 0){
			order = "asc";
		}
		
		fechaDelCalendar.setTime(fechaOficioDel);
		fechaDelCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fechaDelCalendar.set(Calendar.MINUTE, 0);
		fechaDelCalendar.set(Calendar.SECOND, 0);
		fechaDelCalendar.set(Calendar.MILLISECOND, 0);
		fechaAlCalendar.setTime(fechaOficioAl);
		fechaAlCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fechaAlCalendar.set(Calendar.MINUTE, 0);
		fechaAlCalendar.set(Calendar.SECOND, 0);
		fechaAlCalendar.set(Calendar.MILLISECOND, 0);
		
		filters.add("o.fechaOficio between :fechaOficioDel and :fechaOficioAl ");
		whereKeywordNeeded = true;
		namedParameters.put("fechaOficioDel",fechaDelCalendar.getTime());
		namedParameters.put("fechaOficioAl",fechaAlCalendar.getTime());

		sbQl.append("from OficioCnbv as o ");
		if(whereKeywordNeeded){
			sbQl.append(whereKeyword);
			for(String _it : filters){
				if(_it != filters.get(filters.size()-1))
					sbQl.append(_it).append("and ");
				else
					sbQl.append(_it);
			}
		}
		
		strQlCount = "select count(o) " + sbQl.toString();
		sbQl.append("order by o.").append(sort).append(" ").append(order);
				
		SearchResult<OficioCnbv> rs = new SearchResult<OficioCnbv>();
		TypedQuery<Long> tpqCount = em.createQuery(strQlCount.toString(), Long.class);
		TypedQuery<OficioCnbv> tpqRl = em.createQuery(sbQl.toString(), OficioCnbv.class);
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
	
	public boolean checkUniqueClaveDga(String claveDga) {
		String jpql = "select count(o.id) from OficioCnbv as o where o.claveDga = :claveDga";
		Long count = em.createQuery(jpql, Long.class).setParameter("claveDga", claveDga).getSingleResult();
		
		if(count > 0)
			return false;
		else
			return true;
	}

	public boolean checkUniqueNumeroOficio(int numeroOficio) {
		String jpql = "select count(o.id) from OficioCnbv as o where o.numeroOficio = :numeroOficio";
		Long count = em.createQuery(jpql, Long.class).setParameter("numeroOficio", numeroOficio).getSingleResult();
		
		if(count > 0)
			return false;
		else
			return true;
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
