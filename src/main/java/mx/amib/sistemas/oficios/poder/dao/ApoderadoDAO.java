package mx.amib.sistemas.oficios.poder.dao;

import java.util.Collection;
import java.util.List;

import mx.amib.sistemas.oficios.poder.model.Apoderado;

public interface ApoderadoDAO {
	public Apoderado get(Long id);
	public void delete(Long id);
	public Apoderado save(Apoderado a);
	public Apoderado update(Apoderado a);
	
	public List<Apoderado> getAll(Collection<Long> ids);
	public List<Apoderado> findAllByIdCertificacionIn(Collection<Long> idsCertificacion);
}
