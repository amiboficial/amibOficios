package mx.amib.sistemas.oficios.poder.controller.rest;

import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.external.oficios.poder.PoderSearchResultTO;
import mx.amib.sistemas.oficios.poder.service.PoderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poder")
public class PoderRestfulController {
	
	@Autowired
	private PoderService poderService;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ResponseEntity<PoderSearchResultTO> index(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order){
		return null;
	}
	
	@RequestMapping(value="/findAllBy", method = RequestMethod.GET)
	public ResponseEntity<PoderSearchResultTO> findAllBy(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort,
			@RequestParam(value="order", defaultValue="asc") String order){
		return null;
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public PoderTO show(@PathVariable("id") Long id){
		return this.poderService.get(id);
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<PoderTO> save(@RequestBody PoderTO p){
		return null;
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<PoderTO> update(@PathVariable("id") Long id,  @RequestBody PoderTO p){
		//TODO: Actualizar poder y sus respectivos apoderados
		return null;
	}

	public PoderService getPoderService() {
		return poderService;
	}
	public void setPoderService(PoderService poderService) {
		this.poderService = poderService;
	}
}
