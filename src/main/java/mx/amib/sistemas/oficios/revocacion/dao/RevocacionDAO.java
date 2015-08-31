package mx.amib.sistemas.oficios.revocacion.dao;

import mx.amib.sistemas.oficios.revocacion.model.Revocacion;
import mx.amib.sistemas.utils.SearchResult;

public interface RevocacionDAO {
	public SearchResult<Revocacion> findAll(Integer max, Integer offset, String sort, String order);
	public SearchResult<Revocacion> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion);
	
	public SearchResult<Revocacion> findAllByNumeroEscritura(Integer numeroEscritura);
	public SearchResult<Revocacion> findAllByFechaRevocacion(Integer max, Integer offset, String sort, String order,
			Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio, Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio);
	public SearchResult<Revocacion> findAllByGrupoFinanciero(Integer max, Integer offset, String sort, String order,
			Long idGrupoFinanciero);
	public SearchResult<Revocacion> findAllByInstitucion(Integer max, Integer offset, String sort, String order,
			Long idInstitucion);
	
	public Revocacion get(Long id);
	public Revocacion save(Revocacion r);
	public Revocacion update(Revocacion r);
	public void delete(Long id);
	
	public boolean isNumeroEscrituraAvailable(Integer numeroEscritura);
}
