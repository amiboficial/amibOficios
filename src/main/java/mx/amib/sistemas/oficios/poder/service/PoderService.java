package mx.amib.sistemas.oficios.poder.service;

import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.dao.PoderDAO;

public interface PoderService {
	public PoderTO get(Long id);
	
	public PoderDAO getPoderDAO();
	public void setPoderDAO(PoderDAO poderDAO);
}
