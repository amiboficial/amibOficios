package mx.amib.sistemas.oficios.oficioCnbv.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.external.oficios.oficioCnbv.AutorizadoCnbvTO;
import mx.amib.sistemas.external.oficios.oficioCnbv.OficioCnbvTO;
import mx.amib.sistemas.oficios.oficioCnbv.dao.OficioCnbvDAO;
import mx.amib.sistemas.oficios.oficioCnbv.model.AutorizadoCnbv;
import mx.amib.sistemas.oficios.oficioCnbv.model.OficioCnbv;
import mx.amib.sistemas.utils.SearchResult;

@Scope("singleton")
@Service("oficioCnbvService")
public class OficioCnbvServiceImpl implements OficioCnbvService {

	@Autowired
	private OficioCnbvDAO oficioCnbvDAO;
	
	public SearchResult<OficioCnbvTO> index(Integer max, Integer offset,
			String sort, String order) {
		
		SearchResult<OficioCnbv> _sr = this.oficioCnbvDAO.findAll(max, offset, sort, order);
		SearchResult<OficioCnbvTO> sr = this.entityToTransport(_sr);
		return sr;
	}

	public SearchResult<OficioCnbvTO> findAllBy(Integer max, Integer offset,
			String sort, String order, String claveDga, Integer fechaDelDia,
			Integer fechaDelMes, Integer fechaDelAnio, Integer fechaAlDia,
			Integer fechaAlMes, Integer fechaAlAnio) {
		
		SearchResult<OficioCnbv> _sr = this.oficioCnbvDAO.findAllBy(max, offset, sort, order, claveDga, fechaDelDia, fechaDelMes, fechaDelAnio, fechaAlDia, fechaAlMes, fechaAlAnio);
		SearchResult<OficioCnbvTO> sr = this.entityToTransport(_sr);
		
		return sr;
	}

	public SearchResult<OficioCnbvTO> findAllByIdCertificacionInAutorizados(
			Integer max, Integer offset, String sort, String order, Long id) {
		
		SearchResult<OficioCnbv> _sr = this.oficioCnbvDAO.findAllByIdCertificacionInAutorizados(max, offset, sort, order, id);
		SearchResult<OficioCnbvTO> sr = this.entityToTransport(_sr);
		
		return sr;
	}

	public SearchResult<OficioCnbvTO> findAllByMultipleIdCertificacionInAutorizados(
			Integer max, Integer offset, String sort, String order,
			Set<Long> multipleIds) {
		
		SearchResult<OficioCnbv> _sr = this.oficioCnbvDAO.findAllByMultipleIdCertificacionInAutorizados(max, offset, sort, order, multipleIds);
		SearchResult<OficioCnbvTO> sr = this.entityToTransport(_sr);
		
		return sr;
	}

	public OficioCnbvTO get(Long id) {
		return this.entityToTransport( this.oficioCnbvDAO.get(id) );
	}

	public OficioCnbvTO save(OficioCnbvTO o) {
		OficioCnbv _o = new OficioCnbv();
		OficioCnbvTO ores = new OficioCnbvTO();
		
		_o = this.setEntityWithTransportNoChilds(o, _o);
		_o.setVersion(1L);
		_o.setFechaCreacion(Calendar.getInstance().getTime());
		_o.setFechaModificacion(Calendar.getInstance().getTime());
		
		List<AutorizadoCnbv> auts = new ArrayList<AutorizadoCnbv>();
		for(AutorizadoCnbvTO aut : o.getAutorizados()){
			AutorizadoCnbv _aut = new AutorizadoCnbv();
			_aut.setId(null);
			_aut.setIdCertificacion(aut.getIdCertificacion());
			_aut.setOficioCnbv(_o);
			_aut.setFechaCreacion(Calendar.getInstance().getTime());
			_aut.setFechaModificacion(Calendar.getInstance().getTime());
			auts.add(_aut);
		}
		_o.setAutorizados(auts);
		
		try{
			_o = this.oficioCnbvDAO.save(_o);
			ores = this.entityToTransport(_o);
		}
		catch (Exception e) {
			ores.setId(-1L);
		}
		
		return ores;
	}

	@Transactional
	public OficioCnbvTO update(OficioCnbvTO o) {
		OficioCnbvTO ores = new OficioCnbvTO();
		
		List<AutorizadoCnbvTO> nuevosAutorizadoCnbvTO = new ArrayList<AutorizadoCnbvTO>();
		Map<Long,AutorizadoCnbvTO> actualizablesAutorizadoCnbvTO = new HashMap<Long,AutorizadoCnbvTO>();
		Map<Long,AutorizadoCnbv> actualizablesAutorizadoCnbv = new HashMap<Long,AutorizadoCnbv>();
		Map<Long,AutorizadoCnbv> borrablesAutorizadoCnbv = new HashMap<Long,AutorizadoCnbv>();
		
		OficioCnbv _o = this.oficioCnbvDAO.get(o.getId());
		
		//Actualiza datos
		_o = this.setEntityWithTransportNoChilds(o, _o);
		_o.setVersion(_o.getVersion()+1L);
		_o.setFechaModificacion(Calendar.getInstance().getTime());

		//Obtiene a los nuevos y a los actualizables del "TO"
		for(AutorizadoCnbvTO aut : o.getAutorizados()){
			if(aut.getId() == null || aut.getId() <= 0){
				nuevosAutorizadoCnbvTO.add(aut);
			}
			else{
				actualizablesAutorizadoCnbvTO.put(aut.getId(), aut);
			}
		}
		//Obtiene a los actualizables y a los borrables de la instancia de Poder
		for(AutorizadoCnbv _aut : _o.getAutorizados()){
			if( !actualizablesAutorizadoCnbvTO.containsKey(_aut.getId()) ){
				borrablesAutorizadoCnbv.put(_aut.getId(), _aut);
				//System.out.println("NO TUVO LA KEY DE " + _ap.getId());
			}
			else{
				actualizablesAutorizadoCnbv.put(_aut.getId(), _aut);
				//System.out.println("SI TUVO LA KEY DE " + _ap.getId());
			}
		}
		//Quita los autprozadps que ya no esten incluidos en lista
		for(AutorizadoCnbv _aut : borrablesAutorizadoCnbv.values()){
			//System.out.println("VA A BORRAR: " + _ap.getId());
			_o.getAutorizados().remove(_aut);
		}
		//Inserta nuevos autorizados
		for(AutorizadoCnbvTO aut: nuevosAutorizadoCnbvTO){
			AutorizadoCnbv _aut = new AutorizadoCnbv();
			_aut.setId(aut.getId());
			_aut.setIdCertificacion(aut.getIdCertificacion());
			_aut.setOficioCnbv(_o);
			_aut.setFechaCreacion(Calendar.getInstance().getTime());
			_aut.setFechaModificacion(Calendar.getInstance().getTime());
			_o.getAutorizados().add(_aut);
		}
		//Actualiza los apoderados que si se encuentran
		for(AutorizadoCnbvTO aut : actualizablesAutorizadoCnbvTO.values()){
			AutorizadoCnbv _aut = new AutorizadoCnbv();
			_aut = actualizablesAutorizadoCnbv.get(aut.getId());
			_aut.setIdCertificacion(aut.getIdCertificacion());
			_aut.setFechaModificacion(Calendar.getInstance().getTime());
		}
		
		try{
			_o = this.oficioCnbvDAO.update(_o);
			ores = this.entityToTransport(_o);
		}
		catch (Exception e) {
			ores.setId(-1L);
		}
		
		return ores;
	}

	public Boolean delete(Long id) {
		Boolean completed;
		try{
			this.oficioCnbvDAO.delete(id);
			completed = true;
		}
		catch(Exception e){
			completed = false;
		}
		return completed;
	}

	private OficioCnbv setEntityWithTransportNoChilds(OficioCnbvTO o, OficioCnbv _o){
		_o.setClaveDga(o.getClaveDga());
		_o.setNumeroOficio(o.getNumeroOficio());
		_o.setFechaOficio(o.getFechaOficio());
		_o.setUuidDocumentoRespaldo(o.getUuidDocumentoRespaldo());
		return _o;
	}
	
	private SearchResult<OficioCnbvTO> entityToTransport(SearchResult<OficioCnbv> _sr){
		SearchResult<OficioCnbvTO> sr = new SearchResult<OficioCnbvTO>();
		sr.setList(new ArrayList<OficioCnbvTO>());
		sr.setError(false);
		sr.setCount(0L);
		for(OficioCnbv _o : _sr.getList() ){
			OficioCnbvTO o = this.entityToTransport(_o);
			sr.getList().add(o);
		}
		sr.setCount(_sr.getCount());
		sr.setError(_sr.getError());
		return sr;
	}
	
	private OficioCnbvTO entityToTransport(OficioCnbv _o){
		OficioCnbvTO o = new OficioCnbvTO();
		
		o.setId(_o.getId());
		o.setVersion(_o.getVersion());
		
		o.setClaveDga(_o.getClaveDga());
		
		o.setNumeroOficio(_o.getNumeroOficio());
		o.setFechaOficio(_o.getFechaOficio());

		o.setUuidDocumentoRespaldo(_o.getUuidDocumentoRespaldo());
		
		List<AutorizadoCnbvTO> auts = new ArrayList<AutorizadoCnbvTO>();
		for(AutorizadoCnbv _aut : _o.getAutorizados()){
			AutorizadoCnbvTO aut = new AutorizadoCnbvTO();
			aut.setId(_aut.getId());
			aut.setIdCertificacion(_aut.getIdCertificacion());
			aut.setIdOficioCnbv(_o.getId());
			aut.setFechaCreacion(_aut.getFechaCreacion());
			aut.setFechaModificacion(_aut.getFechaModificacion());
			auts.add(aut);
		}
		o.setAutorizados(auts);
		
		o.setFechaCreacion(_o.getFechaCreacion());
		o.setFechaModificacion(_o.getFechaModificacion());
		
		return o;
	}

	
	//Getters y setters de injectado
	public OficioCnbvDAO getOficioCnbvDAO() {
		return oficioCnbvDAO;
	}

	public void setOficioCnbvDAO(OficioCnbvDAO oficioCnbvDAO) {
		this.oficioCnbvDAO = oficioCnbvDAO;
	}
}
