package mx.amib.sistemas.oficios.poder.service;

import mx.amib.sistemas.external.oficios.poder.PoderSearchResultTO;
import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.dao.PoderDAO;

public interface PoderService {
	public PoderSearchResultTO index(Integer max, Integer offset, String sort, String order);
	public PoderSearchResultTO findAllBy(Integer max, Integer offset, String sort, String order);
	public PoderTO get(Long id);
	public PoderTO save(PoderTO p);
	public PoderTO update(PoderTO p);
	
	public PoderDAO getPoderDAO();
	public void setPoderDAO(PoderDAO poderDAO);
}
