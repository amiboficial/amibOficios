package mx.amib.sistemas.oficios.revocacion.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RevocadoService {
	public List<Long> getIdsCertificacionFromIdsRevocado(List<Long> idsRevocado);
	public Map<Long,Boolean> containsRevocados(Set<Long> idsApoderados);
}
