package mx.amib.sistemas.oficios.poder.utils;

import java.util.ArrayList;
import java.util.List;

import mx.amib.sistemas.external.oficios.poder.ApoderadoTO;
import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.model.Apoderado;
import mx.amib.sistemas.oficios.poder.model.Poder;
import mx.amib.sistemas.utils.SearchResult;

public final class TransportEntityConverter {
	
	public static Poder setEntityWithTransportNoChilds(PoderTO p, Poder _p){
		_p.setIdGrupoFinanciero(p.getIdGrupoFinanciero());
		_p.setIdInstitucion(p.getIdInstitucion());
		_p.setIdNotario(p.getIdNotario());
		_p.setNumeroEscritura(p.getNumeroEscritura());
		_p.setRepresentanteLegalNombre(p.getRepresentanteLegalNombre());
		_p.setRepresentanteLegalApellido1(p.getRepresentanteLegalApellido1());
		_p.setRepresentanteLegalApellido2(p.getRepresentanteLegalApellido2());
		_p.setFechaApoderamiento(p.getFechaApoderamiento());
		_p.setUuidDocumentoRespaldo(p.getUuidDocumentoRespaldo());
		return _p;
	}
	
	public static SearchResult<PoderTO> entityToTransport(SearchResult<Poder> _sr){
		SearchResult<PoderTO> sr = new SearchResult<PoderTO>();
		sr.setList(new ArrayList<PoderTO>());
		sr.setError(false);
		sr.setCount(0L);
		for(Poder _p : _sr.getList()){
			PoderTO p = TransportEntityConverter.entityToTransport(_p);
			sr.getList().add(p);
		}
		sr.setError(_sr.getError());
		sr.setCount(_sr.getCount());
		return sr;
	}
	
	public static List<PoderTO> entityToTransport(List<Poder> _lp){
		List<PoderTO> lp = new ArrayList<PoderTO>();
		for(Poder _p : _lp){
			PoderTO p = TransportEntityConverter.entityToTransport(_p);
			lp.add(p);
		}
		return lp;
	}
	
	public static PoderTO entityToTransport(Poder _p){
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
