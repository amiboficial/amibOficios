package mx.amib.sistemas.oficios.poder.controller.rest;

import mx.amib.sistemas.external.oficios.poder.PoderTO;
import mx.amib.sistemas.oficios.poder.service.PoderService;
import mx.amib.sistemas.utils.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<PoderTO> create(){
		return new ResponseEntity<PoderTO>(new PoderTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public SearchResult<PoderTO> index(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order){
		return this.poderService.index(max, offset, sort, order);
	}
	
	@RequestMapping(value="/findAllBy", method = RequestMethod.GET)
	public SearchResult<PoderTO> findAllBy(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, @RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="numeroEscritura", required=false) Integer numeroEscritura, @RequestParam(value="fechaDelDia", required=false) Integer fechaDelDia, @RequestParam(value="fechaDelMes", required=false) Integer fechaDelMes, @RequestParam(value="fechaDelAnio", required=false) Integer fechaDelAnio,  
			@RequestParam(value="fechaAlDia", required=false) Integer fechaAlDia, @RequestParam(value="fechaAlMes", required=false) Integer fechaAlMes, @RequestParam(value="fechaAlAnio", required=false) Integer fechaAlAnio, 
			@RequestParam(value="idGrupoFinanciero", required=false) Long idGrupoFinanciero, @RequestParam(value="idInstitucion", required=false) Long idInstitucion
			){
		return this.poderService.findAllBy(max, offset, sort, order, numeroEscritura, fechaDelDia, fechaDelMes, fechaDelAnio, fechaAlDia, fechaAlMes, fechaAlAnio, idGrupoFinanciero, idInstitucion);
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public PoderTO show(@PathVariable("id") Long id){
		return this.poderService.get(id);
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<PoderTO> save(@RequestBody PoderTO p){
		return new ResponseEntity<PoderTO>( this.poderService.save(p) , HttpStatus.OK);
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<PoderTO> update(@PathVariable("id") Long id,  @RequestBody PoderTO p){
		//Actualiza poder y sus respectivos apoderados
		p.setId(id);
		return new ResponseEntity<PoderTO>( this.poderService.update(p) , HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		Boolean b = this.poderService.delete(id);
		if(b == true){
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
		}
		else{
			return new ResponseEntity<Boolean>( false , HttpStatus.EXPECTATION_FAILED );
		}
	}
	
	//Getters y setters para inyectar dependencias
	public PoderService getPoderService() {
		return poderService;
	}
	public void setPoderService(PoderService poderService) {
		this.poderService = poderService;
	}
}
