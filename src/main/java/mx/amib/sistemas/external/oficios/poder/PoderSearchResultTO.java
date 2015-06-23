package mx.amib.sistemas.external.oficios.poder;

import java.util.List;

public class PoderSearchResultTO {
	List<PoderTO> list;
	Long count;
	Boolean error;
	
	public List<PoderTO> getList() {
		return list;
	}
	public void setList(List<PoderTO> list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}
}
