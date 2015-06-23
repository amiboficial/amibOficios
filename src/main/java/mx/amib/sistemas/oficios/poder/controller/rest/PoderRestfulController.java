package mx.amib.sistemas.oficios.poder.controller.rest;

import mx.amib.sistemas.oficios.poder.model.Poder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poder")
public class PoderRestfulController {
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public Poder show(@PathVariable("id") Long id){
		Poder p = new Poder();
		p.setId(id);
		return p;
	}
}
