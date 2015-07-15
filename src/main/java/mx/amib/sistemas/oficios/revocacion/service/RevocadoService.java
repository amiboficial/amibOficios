package mx.amib.sistemas.oficios.revocacion.service;

import java.util.List;

public interface RevocadoService {
	public List<Long> getIdsCertificacionFromIdsRevocado(List<Long> idsRevocado);
}
