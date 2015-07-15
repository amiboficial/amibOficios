package mx.amib.sistemas.oficios.revocacion.service;

import java.util.ArrayList;
import java.util.List;

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

	
	public RevocadoDAO getRevocadoDAO() {
		return revocadoDAO;
	}

	public void setRevocadoDAO(RevocadoDAO revocadoDAO) {
		this.revocadoDAO = revocadoDAO;
	}
}
