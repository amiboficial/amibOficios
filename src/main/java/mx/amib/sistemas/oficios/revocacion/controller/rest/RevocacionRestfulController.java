package mx.amib.sistemas.oficios.revocacion.controller.rest;

import mx.amib.sistemas.oficios.revocacion.service.RevocacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revocacion")
public class RevocacionRestfulController {
	
	@Autowired
	private RevocacionService revocacionService;

	public RevocacionService getRevocacionService() {
		return revocacionService;
	}

	public void setRevocacionService(RevocacionService revocacionService) {
		this.revocacionService = revocacionService;
	}
	
}
