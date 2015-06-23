package mx.amib.sistemas.oficios.poder.model;

import java.util.Date;

public class Apoderado {
	Long id;
	
	Long idSustentante;
	Long idCertificacion;
	Long idPoder;
	
	Date fechaCreacion;
	Date fechaModificacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdSustentante() {
		return idSustentante;
	}
	public void setIdSustentante(Long idSustentante) {
		this.idSustentante = idSustentante;
	}
	public Long getIdCertificacion() {
		return idCertificacion;
	}
	public void setIdCertificacion(Long idCertificacion) {
		this.idCertificacion = idCertificacion;
	}
	public Long getIdPoder() {
		return idPoder;
	}
	public void setIdPoder(Long idPoder) {
		this.idPoder = idPoder;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
}
