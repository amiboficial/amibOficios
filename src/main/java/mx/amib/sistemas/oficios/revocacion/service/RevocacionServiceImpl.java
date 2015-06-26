package mx.amib.sistemas.oficios.revocacion.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.external.oficios.poder.ApoderadoTO;
import mx.amib.sistemas.external.oficios.revocacion.RevocacionTO;
import mx.amib.sistemas.external.oficios.revocacion.RevocadoTO;
import mx.amib.sistemas.oficios.poder.dao.ApoderadoDAO;
import mx.amib.sistemas.oficios.poder.model.Apoderado;
import mx.amib.sistemas.oficios.poder.model.Poder;
import mx.amib.sistemas.oficios.revocacion.dao.RevocacionDAO;
import mx.amib.sistemas.oficios.revocacion.model.Revocacion;
import mx.amib.sistemas.oficios.revocacion.model.Revocado;
import mx.amib.sistemas.utils.SearchResult;

@Scope("singleton")
@Service("revocacionService")
public class RevocacionServiceImpl implements RevocacionService {

	@Autowired
	private RevocacionDAO revocacionDAO;
	@Autowired
	private ApoderadoDAO apoderadoDAO;
	
	public SearchResult<RevocacionTO> index(Integer max, Integer offset,
			String sort, String order) {
		SearchResult<Revocacion> rsr = this.revocacionDAO.findAll(max, offset, sort, order);
		SearchResult<RevocacionTO> rsrt = this.entityToTransport(rsr);
		return rsrt;
	}

	public SearchResult<RevocacionTO> findAllBy(Integer max, Integer offset,
			String sort, String order, Integer numeroEscritura,
			Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio,
			Long idGrupoFinanciero, Long idInstitucion) {

		SearchResult<Revocacion> rsr = this.revocacionDAO.findAllBy(max, offset, sort, order, 
				numeroEscritura, fechaDelDia, fechaDelMes, fechaDelAnio, 
				fechaAlDia, fechaAlMes, fechaAlAnio, idGrupoFinanciero, idInstitucion);
		SearchResult<RevocacionTO> rsrt = this.entityToTransport(rsr); 
		return rsrt;
	}

	public RevocacionTO get(Long id) {
		return this.entityToTransport( this.revocacionDAO.get(id) );
	}

	public RevocacionTO save(RevocacionTO r) {
		Revocacion _r = new Revocacion();
		RevocacionTO rres = new RevocacionTO();
		
		_r = this.setEntityWithTransportNoChilds(r, _r);
		_r.setVersion(1L);
		_r.setFechaCreacion(Calendar.getInstance().getTime());
		_r.setFechaModificacion(Calendar.getInstance().getTime());
		
		List<Revocado> _rrs = new ArrayList<Revocado>();
		for(RevocadoTO rr : r.getRevocados()){
			Revocado _rr = new Revocado();
			_rr.setId( rr.getId() );
			_rr.setRevocacion( _r );
			_rr.setApoderado( this.apoderadoDAO.get(rr.getIdApoderado()) );
			_rr.setMotivo( rr.getMotivo() );
			_rr.setFechaBaja( rr.getFechaBaja() );
			_rr.setFechaCreacion( Calendar.getInstance().getTime() );
			_rr.setFechaModificacion( Calendar.getInstance().getTime() );
			_rrs.add(_rr);
		}
		_r.setRevocados(_rrs);
		
		try{
			_r = this.revocacionDAO.save(_r);
			rres = this.entityToTransport(_r);
		}
		catch(Exception e){
			rres.setId(-1L);
		}
		
		return rres;
	}

	@Transactional
	public RevocacionTO update(RevocacionTO r) {
		// TODO Auto-generated method stub
		RevocacionTO rres = new RevocacionTO();
		
		List<RevocadoTO> nuevosRevocadosTO = new ArrayList<RevocadoTO>();
		Map<Long,RevocadoTO> actualizablesRevocadosTO = new HashMap<Long,RevocadoTO>();
		Map<Long,Revocado> actualizablesRevocados = new HashMap<Long,Revocado>();
		Map<Long,Revocado> borrablesRevocados = new HashMap<Long,Revocado>();
		
		//Obtiene la instancia actual de Revocacion y sus respectivos Revocados
		Revocacion _r = this.revocacionDAO.get(r.getId());
		
		//Actualiza datos de la revocacion
		_r = this.setEntityWithTransportNoChilds(r, _r);
		_r.setVersion( _r.getVersion() + 1L );
		_r.setFechaModificacion( Calendar.getInstance().getTime() );
		
		//Obtiene a los nuevos y a los actualizables del "TO"
		for(RevocadoTO rr : r.getRevocados() ){
			if(rr.getId() == null || rr.getId() <= 0){
				nuevosRevocadosTO.add(rr);
			}
			else{
				actualizablesRevocadosTO.put(rr.getId(), rr);
			}
		}
		
		//Obtiene a los actualizables y a los borrables de la instancia de Revocado
		for(Revocado _rr : _r.getRevocados()){
			if( !actualizablesRevocadosTO.containsKey(_rr.getId()) ){
				borrablesRevocados.put(_rr.getId(), _rr); 
			}
			else{
				actualizablesRevocados.put(_rr.getId(), _rr);
			}
		}
		
		//Quita los revocados que ya no esten incluidos en lista
		for(Revocado _rr : borrablesRevocados.values()){
			_r.getRevocados().remove(_rr);
		}
		
		//Inserta nuevos revocados
		for(RevocadoTO rr: nuevosRevocadosTO){
			Revocado _rr = new Revocado();
			_rr.setId( rr.getId() );
			_rr.setRevocacion( _r );
			_rr.setApoderado( this.apoderadoDAO.get( rr.getIdApoderado() ) );
			_rr.setMotivo(rr.getMotivo());
			_rr.setFechaBaja(rr.getFechaBaja());
			_rr.setFechaCreacion( Calendar.getInstance().getTime() );
			_rr.setFechaModificacion( Calendar.getInstance().getTime() );
			_r.getRevocados().add(_rr);
		}
		
		//Actualiza los revocados que si se encuentran
		for(RevocadoTO rr : actualizablesRevocadosTO.values()){
			Revocado _rr = null;
			
			_rr = actualizablesRevocados.get(rr.getId());
			_rr.setMotivo(rr.getMotivo());
			_rr.setFechaBaja(rr.getFechaBaja());
			_rr.setFechaModificacion( Calendar.getInstance().getTime() );
		}
		
		try{
			_r = this.revocacionDAO.update(_r);
			rres = this.entityToTransport(_r);
		}
		catch (Exception e) {
			rres.setId(-1L);
		}
		
		return rres;
	}
	
	//Método privados	
	private Revocacion setEntityWithTransportNoChilds(RevocacionTO r, Revocacion _r){
		_r.setIdGrupoFinanciero(r.getIdGrupoFinanciero());
		_r.setIdInstitucion(r.getIdInstitucion());
		_r.setIdNotario(r.getIdNotario());
		_r.setNumeroEscritura(r.getNumeroEscritura());
		_r.setRepresentanteLegalNombre(r.getRepresentanteLegalNombre());
		_r.setRepresentanteLegalApellido1(r.getRepresentanteLegalApellido1());
		_r.setRepresentanteLegalApellido2(r.getRepresentanteLegalApellido2());
		_r.setFechaRevocacion(r.getFechaRevocacion());
		_r.setUuidDocumentoRespaldo(r.getUuidDocumentoRespaldo());
		return _r;
	}

	private SearchResult<RevocacionTO> entityToTransport(SearchResult<Revocacion> _sr){
		SearchResult<RevocacionTO> sr = new SearchResult<RevocacionTO>();
		sr.setList(new ArrayList<RevocacionTO>());
		sr.setError(false);
		sr.setCount(0L);
		for(Revocacion _r : _sr.getList()){
			RevocacionTO r = this.entityToTransport(_r);
			sr.getList().add(r);
		}
		sr.setError(_sr.getError());
		sr.setCount(_sr.getCount());
		return sr;
	}
	
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
			rv.setId(_rv.getId());
			rv.setIdRevocacion(_rv.getRevocacion().getId());
			rv.setIdApoderado(_rv.getApoderado().getId());
			rv.setMotivo(_rv.getMotivo());
			rv.setFechaBaja(_rv.getFechaBaja());
			rv.setFechaCreacion(_rv.getFechaCreacion());
			rv.setFechaModificacion(_rv.getFechaModificacion());
			revocados.add(rv);
		}
		r.setRevocados(revocados);
		
		r.setFechaCreacion(_r.getFechaCreacion());
		r.setFechaModificacion(_r.getFechaModificacion());
		return r;
	}
	
	public ApoderadoDAO getApoderadoDAO() {
		return apoderadoDAO;
	}
	public void setApoderadoDAO(ApoderadoDAO apoderadoDAO) {
		this.apoderadoDAO = apoderadoDAO;
	}
	public RevocacionDAO getRevocacionDAO() {
		return revocacionDAO;
	}
	public void setRevocacionDAO(RevocacionDAO revocacionDAO) {
		this.revocacionDAO = revocacionDAO;
	}
	
}
