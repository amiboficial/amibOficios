package mx.amib.sistemas.oficios.revocacion.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.external.oficios.revocacion.RevocacionTO;
import mx.amib.sistemas.external.oficios.revocacion.RevocadoTO;
import mx.amib.sistemas.oficios.revocacion.dao.RevocacionDAO;
import mx.amib.sistemas.oficios.revocacion.model.Revocacion;
import mx.amib.sistemas.oficios.revocacion.model.Revocado;
import mx.amib.sistemas.utils.SearchResult;

@Scope("singleton")
@Service("poderService")
public class RevocacionServiceImpl implements RevocacionService {

	public SearchResult<RevocacionTO> index(Integer max, Integer offset,
			String sort, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	public SearchResult<RevocacionTO> findAllBy(Integer max, Integer offset,
			String sort, String order, Integer numeroEscritura,
			Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio,
			Long idGrupoFinanciero, Long idInstitucion) {
		// TODO Auto-generated method stub
		return null;
	}

	public RevocacionTO get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public RevocacionTO save(RevocacionTO p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public RevocacionTO update(RevocacionTO p) {
		// TODO Auto-generated method stub
		return null;
	}

	public RevocacionDAO getPoderDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPoderDAO(RevocacionDAO poderDAO) {
		// TODO Auto-generated method stub

	}

	
	//Método privados
	
	private RevocacionTO entityToTransport(Revocacion _r){
		RevocacionTO r = new RevocacionTO();
		
		r.setId(_r.getId());
		r.setVersion(_r.getVersion());
		
		r.setIdGrupoFinanciero(_r.getIdGrupoFinanciero());
		r.setIdInstitucion(_r.getIdInstitucion());
		r.setIdNotario(_r.getIdNotario());
		r.setNumeroEscritura(_r.getNumeroEscritura());
		r.setRepresentanteLegalNombre(_r.getRepresentanteLegalNombre());
		r.setRepresentanteLegalApellido1(_r.getRepresentanteLegalApellido1());
		r.setRepresentanteLegalApellido2(_r.getRepresentanteLegalApellido2());
		r.setFechaRevocacion(_r.getFechaRevocacion());
		r.setUuidDocumentoRespaldo(_r.getUuidDocumentoRespaldo());
		
		List<RevocadoTO> revocados = new ArrayList<RevocadoTO>();
		for(Revocado _rv : _r.getRevocados()){
			RevocadoTO rv = new RevocadoTO();
			//TODO: completar la lista de revocacos y asociarla a la RevocacionTO
		}
		
		r.setFechaCreacion(_r.getFechaCreacion());
		r.setFechaModificacion(_r.getFechaModificacion());
		return r;
	}
}
