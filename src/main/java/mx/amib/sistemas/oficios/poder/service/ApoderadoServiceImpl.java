package mx.amib.sistemas.oficios.poder.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.amib.sistemas.external.oficios.poder.ApoderadoResultTO;
import mx.amib.sistemas.external.oficios.poder.ApoderadoTO;
import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.dao.ApoderadoDAO;
import mx.amib.sistemas.oficios.poder.model.Apoderado;
import mx.amib.sistemas.oficios.poder.model.Poder;
import mx.amib.sistemas.oficios.poder.utils.TransportEntityConverter;

@Scope("singleton")
@Service("apoderadoService")
public class ApoderadoServiceImpl implements ApoderadoService {

	@Autowired
	private ApoderadoDAO apoderadoDAO;
	
	public ApoderadoResultTO findAllByIdCertificacionIn(
			Collection<Long> idsCertificacion) {

		ApoderadoResultTO ares = new ApoderadoResultTO();
		List<Apoderado> _apoderados = this.apoderadoDAO.findAllByIdCertificacionIn(idsCertificacion);
		List<Poder> _poderesDeApoderados = this.collectPoderesFromApoderadosEntity(_apoderados);
		List<PoderTO> poderes = TransportEntityConverter.entityToTransport(_poderesDeApoderados);
		List<ApoderadoTO> apoderados = this.collectApoderadosByIdCertificacion(poderes,idsCertificacion);
		
		ares.setApoderados(apoderados);
		ares.setPoderes(poderes);
		
		return ares;
	}
	
	private List<Poder> collectPoderesFromApoderadosEntity(List<Apoderado> _apoderados){
		Set<Poder> _poderes = new HashSet<Poder>();
		for(Apoderado _a : _apoderados){
			_poderes.add(_a.getPoder());
		}
		return new ArrayList<Poder>(_poderes);
	}
	private List<ApoderadoTO> collectApoderadosByIdCertificacion(List<PoderTO> poderes, Collection<Long> idsCertificacion){
		List<ApoderadoTO> listApoderados = new ArrayList<ApoderadoTO>();
		for(PoderTO p : poderes){
			for(ApoderadoTO a : p.getApoderados()){
				for(Long idCertificacion : idsCertificacion){
					if(a.getIdCertificacion().longValue() == idCertificacion.longValue()){
						listApoderados.add(a);
					}
				}
			}
		}
		return listApoderados;
	}
	
	public ApoderadoDAO getApoderadoDAO() {
		return apoderadoDAO;
	}

	public void setApoderadoDAO(ApoderadoDAO apoderadoDAO) {
		this.apoderadoDAO = apoderadoDAO;
	}

}
