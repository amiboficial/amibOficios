package mx.amib.sistemas.oficios.revocacion.dao;

import java.util.List;
import java.util.Set;

import mx.amib.sistemas.oficios.revocacion.model.Revocado;

public interface RevocadoDAO {
	public List<Revocado> getAll(List<Long> ids);
	public List<Revocado> findAllByIdApoderadoIn(Set<Long> idsApoderado);
}
