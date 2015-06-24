package mx.amib.sistemas.oficios.poder.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.amib.sistemas.external.oficios.poder.ApoderadoTO;
import mx.amib.sistemas.external.oficios.poder.PoderSearchResultTO;
import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.model.Apoderado;
import mx.amib.sistemas.oficios.poder.model.Poder;
import mx.amib.sistemas.oficios.poder.dao.PoderDAO;

@Scope("singleton")
@Service("poderService")
public class PoderServiceImpl implements PoderService {
	
	@Autowired
	private PoderDAO poderDAO;

	public PoderServiceImpl(){
		super();
		//ConvertUtils.register(null, null); <- Registrar aqui el convertidor convert,convertTO
	}
	
	public PoderSearchResultTO index(Integer max, Integer offset, String sort, String order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PoderTO get(Long id){
		return this.entityToTransport(poderDAO.get(id));
	}
	
	//Getters and Setters
	public PoderDAO getPoderDAO() {
		return poderDAO;
	}
	public void setPoderDAO(PoderDAO poderDAO) {
		this.poderDAO = poderDAO;
	}

	public PoderSearchResultTO findAllBy(Integer max, Integer offset, String sort, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	public PoderTO save(PoderTO p) {
		// TODO Auto-generated method stub
		return null;
	}

	public PoderTO update(PoderTO p) {
		// TODO Auto-generated method stub
		return null;
	}

	/*private Poder transportToEntity(Boolean isNew){
		return null;
	}*/
	private PoderTO entityToTransport(Poder _p){
		PoderTO p = new PoderTO();
		
		//TODO: implementar el beanUtil copy
		p.setId(_p.getId());
		p.setVersion(_p.getVersion());
		
		p.setIdGrupoFinanciero(_p.getIdGrupoFinanciero());
		p.setIdInstitucion(_p.getIdInstitucion());
		p.setIdNotario(_p.getIdNotario());
		p.setNumeroEscritura(_p.getNumeroEscritura());
		p.setRepresentanteLegalNombre(_p.getRepresentanteLegalNombre());
		p.setRepresentanteLegalApellido1(_p.getRepresentanteLegalApellido1());
		p.setRepresentanteLegalApellido2(_p.getRepresentanteLegalApellido2());
		p.setFechaApoderamiento(_p.getFechaApoderamiento());
		p.setUuidDocumentoRespaldo(_p.getUuidDocumentoRespaldo());
		
		List<ApoderadoTO> apoderados = new ArrayList<ApoderadoTO>();
		for(Apoderado _a : _p.getApoderados()){
			ApoderadoTO a = new ApoderadoTO();
			a.setId(_a.getId());
			a.setIdCertificacion(_a.getIdCertificacion());
			a.setIdPoder(_a.getPoder().getId());
			a.setFechaCreacion(_a.getFechaCreacion());
			a.setFechaModificacion(_a.getFechaModificacion());
			apoderados.add(a);
		}
		p.setApoderados(apoderados);
		
		p.setFechaCreacion(_p.getFechaCreacion());
		p.setFechaModificacion(_p.getFechaModificacion());
		return p;
	}
}
