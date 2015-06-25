package mx.amib.sistemas.oficios.poder.controller.rest;

import mx.amib.sistemas.external.oficios.poder.ApoderadoTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apoderado")
public class ApoderadoRestfulController {
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ApoderadoTO create(){
		return (new ApoderadoTO());
	}
}
