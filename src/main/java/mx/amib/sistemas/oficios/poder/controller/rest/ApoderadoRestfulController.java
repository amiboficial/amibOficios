package mx.amib.sistemas.oficios.poder.controller.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mx.amib.sistemas.external.oficios.poder.ApoderadoResultTO;
import mx.amib.sistemas.external.oficios.poder.ApoderadoTO;
import mx.amib.sistemas.oficios.poder.service.ApoderadoService;
import mx.amib.sistemas.oficios.poder.service.PoderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apoderado")
public class ApoderadoRestfulController {
	
	@Autowired
	private ApoderadoService apoderadoService;

	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ApoderadoTO create(){
		return (new ApoderadoTO());
	}
	
	@RequestMapping(value="/findAllByIdCertificacionIn", method = RequestMethod.POST)
	public ResponseEntity<ApoderadoResultTO> findAllByIdCertificacionIn(@RequestBody Set<Long> multipleIdsCertificacion){
		ApoderadoResultTO ares = apoderadoService.findAllByIdCertificacionIn(multipleIdsCertificacion);
		return new ResponseEntity<ApoderadoResultTO>( ares , HttpStatus.OK );
	}
	
	public ApoderadoService getApoderadoService() {
		return apoderadoService;
	}

	public void setApoderadoService(ApoderadoService apoderadoService) {
		this.apoderadoService = apoderadoService;
	}
}
