package mx.amib.sistemas.oficios.poder.dao;

import java.util.List;

import mx.amib.sistemas.oficios.poder.model.Poder;

public interface PoderDAO {
	public List<Poder> findAll(Integer max, Integer offset, String sort, String order);
	public List<Poder> findAllBy(Integer max, Integer offset, String sort, String order);
	public Poder get(Long id);
	public Poder save(Poder s);
	public Poder update(Poder s);
}
