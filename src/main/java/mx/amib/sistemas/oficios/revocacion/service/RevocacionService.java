package mx.amib.sistemas.oficios.revocacion.service;

import java.util.Set;

import mx.amib.sistemas.external.oficios.revocacion.RevocacionTO;
import mx.amib.sistemas.utils.SearchResult;

public interface RevocacionService {
	public SearchResult<RevocacionTO> index(Integer max, Integer offset, String sort, String order);
	public SearchResult<RevocacionTO> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion);
	
	public SearchResult<RevocacionTO> findAllByNumeroEscritura(Integer numeroEscritura);
	public SearchResult<RevocacionTO> findAllByFechaRevocacion(Integer max, Integer offset, String sort, String order,
			Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio, Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio);
	public SearchResult<RevocacionTO> findAllByGrupoFinanciero(Integer max, Integer offset, String sort, String order,
			Long idGrupoFinanciero);
	public SearchResult<RevocacionTO> findAllByInstitucion(Integer max, Integer offset, String sort, String order,
			Long idInstitucion);
	
	public Set<RevocacionTO> getAllByIdCertficacionInSet(Set<Long> idsCertficacion);
	
	public RevocacionTO get(Long id);
	public RevocacionTO save(RevocacionTO r);
	public RevocacionTO update(RevocacionTO r);
	public Boolean delete(Long id);
	public Boolean isNumeroEscrituraAvailable(int numeroEscritura);
}
