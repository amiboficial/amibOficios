package mx.amib.sistemas.oficios.oficioCnbv.dao;

import java.util.Date;
import java.util.Set;

import mx.amib.sistemas.utils.SearchResult;
import mx.amib.sistemas.oficios.oficioCnbv.model.OficioCnbv;

public interface OficioCnbvDAO {
	public SearchResult<OficioCnbv> findAll(Integer max, Integer offset, String sort, String order);
	public SearchResult<OficioCnbv> findAllBy(Integer max, Integer offset, String sort, String order, 
			String claveDga, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio);
	public SearchResult<OficioCnbv> findAllByIdCertificacionInAutorizados(Integer max, Integer offset, String sort, String order, Long id);
	public SearchResult<OficioCnbv> findAllByMultipleIdCertificacionInAutorizados(Integer max, Integer offset, String sort, String order, Set<Long> multipleIds);
	public SearchResult<OficioCnbv> findAllByNumeroOficio(Integer numeroOficio);
	public SearchResult<OficioCnbv> findAllByClaveDga(String claveDga);
	public SearchResult<OficioCnbv> findAllByFechaOficio(Integer max, Integer offset, String sort, String order,
			Date fechaOficioDel, Date fechaOficioAl);
	public boolean checkUniqueClaveDga(String claveDga);
	public boolean checkUniqueNumeroOficio(int numeroOficio);
	public OficioCnbv get(Long id);
	public OficioCnbv save(OficioCnbv of);
	public OficioCnbv update(OficioCnbv of);
	public void delete(Long id);
}
