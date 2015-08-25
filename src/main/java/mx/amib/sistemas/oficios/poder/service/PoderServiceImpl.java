package mx.amib.sistemas.oficios.poder.service;

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
import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.model.Apoderado;
import mx.amib.sistemas.oficios.poder.model.Poder;
import mx.amib.sistemas.oficios.poder.utils.TransportEntityConverter;
import mx.amib.sistemas.oficios.poder.dao.PoderDAO;
import mx.amib.sistemas.utils.SearchResult;

@Scope("singleton")
@Service("poderService")
public class PoderServiceImpl implements PoderService {
	
	@Autowired
	private PoderDAO poderDAO;
	
	public PoderServiceImpl(){
		super();
	}
		
	public SearchResult<PoderTO> index(Integer max, Integer offset, String sort, String order) {
		SearchResult<Poder> rsp = poderDAO.findAll(max, offset, sort, order);
		SearchResult<PoderTO> rspt = TransportEntityConverter.entityToTransport(rsp);
		
		return rspt;
	}
	
	public PoderTO get(Long id){
		return TransportEntityConverter.entityToTransport(poderDAO.get(id));
	}
	
	public SearchResult<PoderTO> findAllBy(Integer max, Integer offset, String sort, String order, 
			Integer numeroEscritura, Integer fechaDelDia, Integer fechaDelMes, Integer fechaDelAnio,  
			Integer fechaAlDia, Integer fechaAlMes, Integer fechaAlAnio, 
			Long idGrupoFinanciero, Long idInstitucion) {
		
		SearchResult<Poder> rsp = poderDAO.findAllBy(max, offset, sort, order, 
				numeroEscritura, fechaDelDia, fechaDelMes, fechaDelAnio, 
				fechaAlDia, fechaAlMes, fechaAlAnio, idGrupoFinanciero, idInstitucion);
		SearchResult<PoderTO> rspt = TransportEntityConverter.entityToTransport(rsp);
		
		return rspt;
	}

	public PoderTO save(PoderTO p) {
		Poder _p = new Poder();
		PoderTO pres = new PoderTO();
		
		_p = TransportEntityConverter.setEntityWithTransportNoChilds(p, _p);
		_p.setVersion(1L);
		_p.setFechaCreacion(Calendar.getInstance().getTime());
		_p.setFechaModificacion(Calendar.getInstance().getTime());
		
		List<Apoderado> _aps = new ArrayList<Apoderado>();
		for(ApoderadoTO a : p.getApoderados()){
			Apoderado _a = new Apoderado();
			_a.setId(null);
			_a.setIdCertificacion(a.getIdCertificacion());
			_a.setPoder(_p);
			_a.setFechaCreacion(Calendar.getInstance().getTime());
			_a.setFechaModificacion(Calendar.getInstance().getTime());
			_aps.add(_a);
		}
		_p.setApoderados(_aps);
		
		try{
			_p = this.poderDAO.save(_p);
			pres = TransportEntityConverter.entityToTransport(_p);
		}
		catch (Exception e) {
			pres.setId(-1L);
		}
		
		return pres;
	}

	@Transactional
	public PoderTO update(PoderTO p) {
		
		PoderTO pres = new PoderTO();
		
		List<ApoderadoTO> nuevosApoderadosTO = new ArrayList<ApoderadoTO>();
		Map<Long,ApoderadoTO> actualizablesApoderadosTO = new HashMap<Long,ApoderadoTO>();
		Map<Long,Apoderado> actualizablesApoderados = new HashMap<Long,Apoderado>();
		Map<Long,Apoderado> borrablesApoderados = new HashMap<Long,Apoderado>();
		
		//Obtiene la instancia actual de Poder y sus respectivos Apoderados
		Poder _p = this.poderDAO.get(p.getId());
		
		//Actualiza datos de poder
		_p = TransportEntityConverter.setEntityWithTransportNoChilds(p, _p);
		_p.setVersion(_p.getVersion() + 1L);
		_p.setFechaModificacion(Calendar.getInstance().getTime());
		
		//Obtiene a los nuevos y a los actualizables del "TO"
		for(ApoderadoTO ap : p.getApoderados()){
			if(ap.getId() == null || ap.getId() <= 0){
				nuevosApoderadosTO.add(ap);
			}
			else{
				actualizablesApoderadosTO.put(ap.getId(), ap);
			}
		}
		//Obtiene a los actualizables y a los borrables de la instancia de Poder
		for(Apoderado _ap : _p.getApoderados()){
			if( !actualizablesApoderadosTO.containsKey(_ap.getId()) ){
				borrablesApoderados.put(_ap.getId(), _ap);
				//System.out.println("NO TUVO LA KEY DE " + _ap.getId());
			}
			else{
				actualizablesApoderados.put(_ap.getId(), _ap);
				//System.out.println("SI TUVO LA KEY DE " + _ap.getId());
			}
		}
		//Quita los apoderados que ya no esten incluidos en lista
		for(Apoderado _ap : borrablesApoderados.values()){
			//System.out.println("VA A BORRAR: " + _ap.getId());
			_p.getApoderados().remove(_ap);
			//this.apoderadoDAO.delete(_ap.getId()); no es necesario por que ya tiene la etiqueta "orphan-remove"
		}
		//Inserta nuevos apoderados
		for(ApoderadoTO ap: nuevosApoderadosTO){
			Apoderado _ap = new Apoderado();
			_ap.setId(ap.getId());
			_ap.setIdCertificacion(ap.getIdCertificacion());
			_ap.setPoder(_p);
			_ap.setFechaCreacion(Calendar.getInstance().getTime());
			_ap.setFechaModificacion(Calendar.getInstance().getTime());
			_p.getApoderados().add(_ap);
		}
		//Actualiza los apoderados que si se encuentran
		for(ApoderadoTO ap : actualizablesApoderadosTO.values()){
			Apoderado _ap = null;
			_ap = actualizablesApoderados.get(ap.getId());
			
			_ap.setIdCertificacion(ap.getIdCertificacion());
			_ap.setPoder(_p);
			_ap.setFechaModificacion(Calendar.getInstance().getTime());
		}
		
		try{
			_p = this.poderDAO.update(_p);
			pres = TransportEntityConverter.entityToTransport(_p);
		}
		catch (Exception e) {
			pres.setId(-1L);
		}
		
		return pres;
	}

	public Boolean delete(Long id) {
		Boolean completed;
		try{
			this.poderDAO.delete(id);
			completed = true;
		}
		catch(Exception e){
			completed = false;
		}
		return completed;
	}
	public Boolean isNumeroEscrituraAvailable(Integer numeroEscritura) {
		return this.poderDAO.isNumeroEscrituraAvailable(numeroEscritura);
	}

	
	//Getters and Setters
	public PoderDAO getPoderDAO() {
		return poderDAO;
	}
	public void setPoderDAO(PoderDAO poderDAO) {
		this.poderDAO = poderDAO;
	}
}
