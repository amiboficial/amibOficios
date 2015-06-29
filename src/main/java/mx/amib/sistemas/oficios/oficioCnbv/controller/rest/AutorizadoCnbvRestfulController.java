package mx.amib.sistemas.oficios.oficioCnbv.controller.rest;

import mx.amib.sistemas.external.oficios.oficioCnbv.AutorizadoCnbvTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autorizadoCnbv")
public class AutorizadoCnbvRestfulController {
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<AutorizadoCnbvTO> create(){
		return new ResponseEntity<AutorizadoCnbvTO>(new AutorizadoCnbvTO(), HttpStatus.OK);
	}
}
