package mx.amib.sistemas.oficios.oficioCnbv.service;

import java.util.Set;

import mx.amib.sistemas.external.oficios.oficioCnbv.OficioCnbvTO;
import mx.amib.sistemas.utils.SearchResult;

public interface OficioCnbvService {
	public SearchResult<OficioCnbvTO> index(Integer max, Integer offset, String sort, String order);
	public SearchResult<OficioCnbvTO> findAllBy(Integer max, Integer offset, String sort, String order, 
			String claveDga, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio);
	public SearchResult<OficioCnbvTO> findAllByIdCertificacionInAutorizados(Integer max, Integer offset, String sort, String order, Long id);
	public SearchResult<OficioCnbvTO> findAllByMultipleIdCertificacionInAutorizados(Integer max, Integer offset, String sort, String order, Set<Long> multipleIds);
	public OficioCnbvTO get(Long id);
	public OficioCnbvTO save(OficioCnbvTO o);
	public OficioCnbvTO update(OficioCnbvTO o);
	public Boolean delete(Long id);
}
