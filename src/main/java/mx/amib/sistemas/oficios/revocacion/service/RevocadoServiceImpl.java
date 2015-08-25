package mx.amib.sistemas.oficios.revocacion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.amib.sistemas.oficios.revocacion.dao.RevocadoDAO;
import mx.amib.sistemas.oficios.revocacion.model.Revocado;

@Scope("singleton")
@Service("revocadoService")
public class RevocadoServiceImpl implements RevocadoService {

	@Autowired
	private RevocadoDAO revocadoDAO;
	
	public List<Long> getIdsCertificacionFromIdsRevocado(
			List<Long> multipleIdRevocado) {
		
		List<Revocado> _revs = this.revocadoDAO.getAll(multipleIdRevocado);
		List<Long> idsCertificacionFromIdsRevocados = new ArrayList<Long>();
		
		for(Revocado _rev : _revs){
			idsCertificacionFromIdsRevocados.add(_rev.getApoderado().getIdCertificacion());
		}
		
		return idsCertificacionFromIdsRevocados;
	}

	public Map<Long, Boolean> containsRevocados(Set<Long> idsApoderados) {
		Map<Long, Boolean> apoderadosRevocados = new HashMap<Long, Boolean>();
		List<Revocado> revocadosFound = this.revocadoDAO.findAllByIdApoderadoIn(idsApoderados);
		Iterator<Revocado> iteratorRevocadosFound = null;
		Revocado revocadoInstance = null;
		
		for(Long idApoderado : idsApoderados){
			apoderadosRevocados.put(idApoderado, false);
			
			iteratorRevocadosFound = revocadosFound.iterator();
			while(iteratorRevocadosFound.hasNext()){
				revocadoInstance = iteratorRevocadosFound.next();
				if(revocadoInstance.getApoderado().getId().longValue() == idApoderado.longValue()){
					apoderadosRevocados.put(idApoderado, true);
					break;
				}
			}
		}
		
		return apoderadosRevocados;
	}
	
	public RevocadoDAO getRevocadoDAO() {
		return revocadoDAO;
	}

	public void setRevocadoDAO(RevocadoDAO revocadoDAO) {
		this.revocadoDAO = revocadoDAO;
	}

}
