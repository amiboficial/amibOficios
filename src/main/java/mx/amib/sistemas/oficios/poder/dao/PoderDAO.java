package mx.amib.sistemas.oficios.poder.dao;

import mx.amib.sistemas.utils.SearchResult;
import mx.amib.sistemas.oficios.poder.model.Poder;

public interface PoderDAO {
	public SearchResult<Poder> findAll(Integer max, Integer offset, String sort, String order);
	public SearchResult<Poder> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion);
	public Poder get(Long id);
	public Poder save(Poder p);
	public Poder update(Poder p);
	public void delete(Long id);
	
	public boolean isNumeroEscrituraAvailable(Integer numeroEscritura);
}
