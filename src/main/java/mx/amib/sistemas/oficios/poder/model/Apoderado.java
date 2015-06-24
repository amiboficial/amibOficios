package mx.amib.sistemas.oficios.poder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="t102_t_apoderado")
public class Apoderado implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name="id_f_certificacion")
	Long idCertificacion;
	@ManyToOne
	@JoinColumn(name="id_101_poder")
	Poder poder;

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
	public Poder getPoder() {
		return poder;
	}
	public void setPoder(Poder poder) {
		this.poder = poder;
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
	
	
	private static final long serialVersionUID = 1L;
	
}
