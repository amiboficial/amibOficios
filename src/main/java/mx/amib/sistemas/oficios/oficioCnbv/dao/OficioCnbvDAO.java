package mx.amib.sistemas.oficios.oficioCnbv.dao;

import java.util.Set;

import mx.amib.sistemas.utils.SearchResult;
import mx.amib.sistemas.oficios.oficioCnbv.model.OficioCnbv;

public interface OficioCnbvDAO {
	public SearchResult<OficioCnbv> findAll(Integer max, Integer offset, String sort, String order);
	public SearchResult<OficioCnbv> findAllBy(Integer max, Integer offset, String sort, String order, 
			String claveDga, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio);
	public SearchResult<OficioCnbv> findAllByIdCertificacionInAutorizados(Long id);
	public SearchResult<OficioCnbv> findAllByMultipleIdCertificacionInAutorizados(Set<Long> multipleIds);
	public OficioCnbv get(Long id);
	public OficioCnbv save(OficioCnbv of);
	public OficioCnbv update(OficioCnbv of);
	public void delete(Long id);
}
