package mx.amib.sistemas.oficios.poder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t102_t_apoderado")
public class Apoderado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name="id_f_certificacion")
	Long idCertificacion;
	@Column(name="id_101_poder")
	Long idPoder;

	@Column(name="fh_creacion")
	Date fechaCreacion;
	@Column(name="fh_modificacion")
	Date fechaModificacion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
