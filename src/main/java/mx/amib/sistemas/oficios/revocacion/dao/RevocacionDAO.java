package mx.amib.sistemas.oficios.revocacion.dao;

import mx.amib.sistemas.oficios.revocacion.model.Revocacion;
import mx.amib.sistemas.utils.SearchResult;

public interface RevocacionDAO {
	public SearchResult<Revocacion> findAll(Integer max, Integer offset, String sort, String order);
	public SearchResult<Revocacion> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion);
	public Revocacion get(Long id);
	public Revocacion save(Revocacion r);
	public Revocacion update(Revocacion r);
}
