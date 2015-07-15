package mx.amib.sistemas.oficios.revocacion.dao;

import java.util.List;

import mx.amib.sistemas.oficios.revocacion.model.Revocado;

public interface RevocadoDAO {
	public List<Revocado> getAll(List<Long> ids);
}
