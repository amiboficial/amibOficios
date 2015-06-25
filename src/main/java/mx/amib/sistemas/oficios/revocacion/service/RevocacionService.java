package mx.amib.sistemas.oficios.revocacion.service;

import mx.amib.sistemas.external.oficios.revocacion.RevocacionTO;
import mx.amib.sistemas.oficios.revocacion.dao.RevocacionDAO;
import mx.amib.sistemas.utils.SearchResult;

public interface RevocacionService {
	public SearchResult<RevocacionTO> index(Integer max, Integer offset, String sort, String order);
	public SearchResult<RevocacionTO> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion);
	public RevocacionTO get(Long id);
	public RevocacionTO save(RevocacionTO p);
	public RevocacionTO update(RevocacionTO p);
	
	public RevocacionDAO getPoderDAO();
	public void setPoderDAO(RevocacionDAO poderDAO);
}
