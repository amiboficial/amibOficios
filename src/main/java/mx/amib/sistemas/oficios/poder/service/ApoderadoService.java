package mx.amib.sistemas.oficios.poder.service;

import java.util.Collection;

import mx.amib.sistemas.external.oficios.poder.ApoderadoResultTO;

public interface ApoderadoService {
	public ApoderadoResultTO findAllByIdCertificacionIn(Collection<Long> idsCertificacion);
}
