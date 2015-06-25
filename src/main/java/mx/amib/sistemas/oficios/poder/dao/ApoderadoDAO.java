package mx.amib.sistemas.oficios.poder.dao;

import mx.amib.sistemas.oficios.poder.model.Apoderado;

public interface ApoderadoDAO {
	public Apoderado get(Long id);
	public void delete(Long id);
	public Apoderado save(Apoderado a);
	public Apoderado update(Apoderado a);
}
