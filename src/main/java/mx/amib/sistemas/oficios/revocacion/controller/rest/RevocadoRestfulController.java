package mx.amib.sistemas.oficios.revocacion.controller.rest;

import java.util.List;

import mx.amib.sistemas.external.oficios.revocacion.RevocadoTO;
import mx.amib.sistemas.oficios.revocacion.service.RevocadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revocado")
public class RevocadoRestfulController {
	
	@Autowired
	private RevocadoService revocadoService;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<RevocadoTO> create(){
		return new ResponseEntity<RevocadoTO>(new RevocadoTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getIdsCertificacionFromIdsRevocados", method = RequestMethod.POST)
	public ResponseEntity<List<Long>> getIdsCertificacion(@RequestBody List<Long> ids){
		List<Long> idsCert = revocadoService.getIdsCertificacionFromIdsRevocado(ids);
		return new ResponseEntity<List<Long>>(idsCert, HttpStatus.OK);
	}

	
	//Getters y setters
	public RevocadoService getRevocadoService() {
		return revocadoService;
	}

	public void setRevocadoService(RevocadoService revocadoService) {
		this.revocadoService = revocadoService;
	}
}
