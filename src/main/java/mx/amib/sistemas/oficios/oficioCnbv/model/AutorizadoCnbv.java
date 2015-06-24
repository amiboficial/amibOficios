package mx.amib.sistemas.oficios.oficioCnbv.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="t106_t_autorizadocnbv")
public class AutorizadoCnbv implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name="id_f_certificacion")
	Long idCertificacion;
	@Column(name="id_110_oficiocnbv")
	Long idOficioCnbv;
	
	@Column(name="fh_creacion")
	private Date fechaCreacion;
	@Column(name="fh_modificacion")
	private Date fechaModificacion;
	
	
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
	public Long getIdOficioCnbv() {
		return idOficioCnbv;
	}
	public void setIdOficioCnbv(Long idOficioCnbv) {
		this.idOficioCnbv = idOficioCnbv;
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
	
	private static final long serialVersionUID = 5769443743852651954L;
}
