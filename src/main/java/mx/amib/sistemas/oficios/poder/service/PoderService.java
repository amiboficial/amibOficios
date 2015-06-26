package mx.amib.sistemas.oficios.poder.service;

import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.utils.SearchResult;

public interface PoderService {
	public SearchResult<PoderTO> index(Integer max, Integer offset, String sort, String order);
	public SearchResult<PoderTO> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion);
	public PoderTO get(Long id);
	public PoderTO save(PoderTO p);
	public PoderTO update(PoderTO p);
}
