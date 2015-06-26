package mx.amib.sistemas.oficios.revocacion.controller.rest;

import mx.amib.sistemas.external.oficios.revocacion.RevocadoTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revocado")
public class RevocadoRestfulController {
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<RevocadoTO> create(){
		return new ResponseEntity<RevocadoTO>(new RevocadoTO(), HttpStatus.OK);
	}
}
