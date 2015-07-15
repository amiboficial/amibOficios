package mx.amib.sistemas.oficios.oficioCnbv.controller.rest;

import java.util.Set;

import mx.amib.sistemas.external.oficios.oficioCnbv.OficioCnbvTO;
import mx.amib.sistemas.oficios.oficioCnbv.service.OficioCnbvService;
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
@RequestMapping("/oficioCnbv")
public class OficioCnbvRestfulController {
	
	@Autowired
	private OficioCnbvService oficioCnbvService;
	

	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<OficioCnbvTO> create(){
		return new ResponseEntity<OficioCnbvTO>(new OficioCnbvTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<OficioCnbvTO>> index(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order){		
		return new ResponseEntity<SearchResult<OficioCnbvTO>>( 
			this.oficioCnbvService.index(max, offset, sort, order), HttpStatus.OK 
		);
	}
	
	@RequestMapping(value="/findAllBy", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<OficioCnbvTO>> findAllBy(@RequestParam(value="max", defaultValue="10") Integer max, @RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, @RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="claveDga", required=false) String claveDga, 
			@RequestParam(value="fechaDelDia", required=false) Integer fechaDelDia, @RequestParam(value="fechaDelMes", required=false) Integer fechaDelMes, @RequestParam(value="fechaDelAnio", required=false) Integer fechaDelAnio,  
			@RequestParam(value="fechaAlDia", required=false) Integer fechaAlDia, @RequestParam(value="fechaAlMes", required=false) Integer fechaAlMes, @RequestParam(value="fechaAlAnio", required=false) Integer fechaAlAnio
			){
		return new ResponseEntity<SearchResult<OficioCnbvTO>>( 
				this.oficioCnbvService.findAllBy(max, offset, sort, order, claveDga, fechaDelDia, fechaDelMes, fechaDelAnio, fechaAlDia, fechaAlMes, fechaAlAnio), HttpStatus.OK 
		);
	}
	
	@RequestMapping(value="/findAllByIdCertificacionInAutorizados", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<OficioCnbvTO>> findAllByIdCertificacionInAutorizados(@RequestParam(value="max", defaultValue="10") Integer max, @RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, @RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="idCertificacion", required=true) Long idCertificacion){
		return new ResponseEntity<SearchResult<OficioCnbvTO>>(
			this.oficioCnbvService.findAllByIdCertificacionInAutorizados(max, offset, sort, order, idCertificacion), HttpStatus.OK 
		);
	}
	
	@RequestMapping(value="/findAllByMultipleIdCertificacionInAutorizados", method = RequestMethod.POST)
	public ResponseEntity<SearchResult<OficioCnbvTO>> findAllByMultipleIdCertificacionInAutorizados(@RequestParam(value="max", defaultValue="10") Integer max, @RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, @RequestParam(value="order", defaultValue="asc") String order, 
			@RequestBody Set<Long> multipleIds){
		return new ResponseEntity<SearchResult<OficioCnbvTO>>( 
			this.oficioCnbvService.findAllByMultipleIdCertificacionInAutorizados(max, offset, sort, order, multipleIds), HttpStatus.OK 
		);
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public ResponseEntity<OficioCnbvTO> show(@PathVariable("id") Long id){
		return new ResponseEntity<OficioCnbvTO>( this.oficioCnbvService.get(id) , HttpStatus.OK);
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<OficioCnbvTO> save(@RequestBody OficioCnbvTO o){
		return new ResponseEntity<OficioCnbvTO>( this.oficioCnbvService.save(o) , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<OficioCnbvTO> update(@PathVariable("id") Long id,  @RequestBody OficioCnbvTO o){
		o.setId(id);
		return new ResponseEntity<OficioCnbvTO>( this.oficioCnbvService.update(o) , HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		Boolean b = this.oficioCnbvService.delete(id);
		if(b == true){
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
		}
		else{
			return new ResponseEntity<Boolean>( false , HttpStatus.EXPECTATION_FAILED );
		}
	}
	
	//Getters y setters de atributos inyectados
	public OficioCnbvService getOficioCnbvService() {
		return oficioCnbvService;
	}
	public void setOficioCnbvService(OficioCnbvService oficioCnbvService) {
		this.oficioCnbvService = oficioCnbvService;
	}
}
