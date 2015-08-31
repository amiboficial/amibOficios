package mx.amib.sistemas.oficios.revocacion.controller.rest;

import mx.amib.sistemas.external.oficios.revocacion.RevocacionTO;
import mx.amib.sistemas.oficios.revocacion.service.RevocacionService;
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
@RequestMapping("/revocacion")
public class RevocacionRestfulController {
	
	@Autowired
	private RevocacionService revocacionService;

	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<RevocacionTO> create(){
		return new ResponseEntity<RevocacionTO>(new RevocacionTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public SearchResult<RevocacionTO> index(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order){		
		return this.revocacionService.index(max, offset, sort, order);
	}
	
	@RequestMapping(value="/findAllBy", method = RequestMethod.GET)
	public SearchResult<RevocacionTO> findAllBy(@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, @RequestParam(value="sort", defaultValue="id") String sort, @RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="numeroEscritura", required=false) Integer numeroEscritura, @RequestParam(value="fechaDelDia", required=false) Integer fechaDelDia, @RequestParam(value="fechaDelMes", required=false) Integer fechaDelMes, @RequestParam(value="fechaDelAnio", required=false) Integer fechaDelAnio,  
			@RequestParam(value="fechaAlDia", required=false) Integer fechaAlDia, @RequestParam(value="fechaAlMes", required=false) Integer fechaAlMes, @RequestParam(value="fechaAlAnio", required=false) Integer fechaAlAnio, 
			@RequestParam(value="idGrupoFinanciero", required=false) Long idGrupoFinanciero, @RequestParam(value="idInstitucion", required=false) Long idInstitucion
			){
		return this.revocacionService.findAllBy(max, offset, sort, order, numeroEscritura, fechaDelDia, fechaDelMes, fechaDelAnio, fechaAlDia, fechaAlMes, fechaAlAnio, idGrupoFinanciero, idInstitucion);
	}
	
	@RequestMapping(value="/findAllByNumeroEscritura", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<RevocacionTO>> findAllByNumeroEscritura(
			@RequestParam(value="numeroEscritura", required=true) Integer numeroEscritura){
		
		SearchResult<RevocacionTO> searchResult;
		
		searchResult = this.revocacionService.findAllByNumeroEscritura(numeroEscritura);
		
		return new ResponseEntity<SearchResult<RevocacionTO>>( searchResult , HttpStatus.OK);
	}
	
	@RequestMapping(value="/findAllByFechaRevocacion", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<RevocacionTO>> findAllByFechaRevocacion(
			@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, 
			@RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="fechaDelDia", required=false) Integer fechaDelDia, 
			@RequestParam(value="fechaDelMes", required=false) Integer fechaDelMes, 
			@RequestParam(value="fechaDelAnio", required=false) Integer fechaDelAnio,  
			@RequestParam(value="fechaAlDia", required=false) Integer fechaAlDia, 
			@RequestParam(value="fechaAlMes", required=false) Integer fechaAlMes, 
			@RequestParam(value="fechaAlAnio", required=false) Integer fechaAlAnio){
		
		SearchResult<RevocacionTO> searchResult;
		
		searchResult = this.revocacionService.findAllByFechaRevocacion(max,offset,sort,order,
				fechaDelDia,fechaDelMes,fechaDelAnio,fechaAlDia,fechaAlMes,fechaAlAnio);
		
		return new ResponseEntity<SearchResult<RevocacionTO>>( searchResult , HttpStatus.OK );
	}
	
	@RequestMapping(value="/findAllByGrupoFinanciero", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<RevocacionTO>> findAllByGrupoFinanciero(
			@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, 
			@RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="idGrupoFinanciero", required=true) Long idGrupoFinanciero){
		
		SearchResult<RevocacionTO> searchResult;
		
		searchResult = this.revocacionService.findAllByGrupoFinanciero(max,offset,sort,order,
																		idGrupoFinanciero);
		
		return new ResponseEntity<SearchResult<RevocacionTO>>( searchResult , HttpStatus.OK);
	}
	
	@RequestMapping(value="/findAllByInstitucion", method = RequestMethod.GET)
	public ResponseEntity<SearchResult<RevocacionTO>> findAllByInstitucion(
			@RequestParam(value="max", defaultValue="10") Integer max, 
			@RequestParam(value="offset", defaultValue="0") Integer offset, 
			@RequestParam(value="sort", defaultValue="id") String sort, 
			@RequestParam(value="order", defaultValue="asc") String order, 
			@RequestParam(value="idInstitucion", required=true) Long idInstitucion){
		
		SearchResult<RevocacionTO> searchResult;
		
		searchResult = this.revocacionService.findAllByInstitucion(max,offset,sort,order,
																	idInstitucion);
		
		return new ResponseEntity<SearchResult<RevocacionTO>>( searchResult , HttpStatus.OK);
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public ResponseEntity<RevocacionTO> show(@PathVariable("id") Long id){
		return new ResponseEntity<RevocacionTO>( this.revocacionService.get(id) , HttpStatus.OK);
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<RevocacionTO> save(@RequestBody RevocacionTO r){
		return new ResponseEntity<RevocacionTO>( this.revocacionService.save(r) , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RevocacionTO> update(@PathVariable("id") Long id,  @RequestBody RevocacionTO r){
		//Actualiza la revocacion y sus respectivos apoderados
		r.setId(id);
		return new ResponseEntity<RevocacionTO>( this.revocacionService.update(r) , HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		Boolean b = this.revocacionService.delete(id);
		if(b == true){
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
		}
		else{
			return new ResponseEntity<Boolean>( false , HttpStatus.EXPECTATION_FAILED );
		}
	}
	
	@RequestMapping(value="/isNumeroEscrituraAvailable/{numeroEscritura}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> isNumeroEscrituraAvailable(@PathVariable("numeroEscritura") Integer numeroEscritura){
		Boolean b = this.revocacionService.isNumeroEscrituraAvailable(numeroEscritura);
		if(b == true){
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
		}
		else{
			return new ResponseEntity<Boolean>( false , HttpStatus.EXPECTATION_FAILED );
		}
	}
	
	public RevocacionService getRevocacionService() {
		return revocacionService;
	}
	public void setRevocacionService(RevocacionService revocacionService) {
		this.revocacionService = revocacionService;
	}
	
}
