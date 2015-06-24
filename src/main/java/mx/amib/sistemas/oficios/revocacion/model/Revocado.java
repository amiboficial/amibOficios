package mx.amib.sistemas.oficios.revocacion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t104_t_revocado")
public class Revocado implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="id_105_revocacion")
	private Long idRevocacion;
	@Column(name="id_102_apoderado")
	private Long idApoderado;
	
	@Column(name="tx_motivo")
	private String motivo;
	@Column(name="fh_baja")
	private Date fechaBaja;
	
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
	public Long getIdRevocacion() {
		return idRevocacion;
	}
	public void setIdRevocacion(Long idRevocacion) {
		this.idRevocacion = idRevocacion;
	}
	public Long getIdApoderado() {
		return idApoderado;
	}
	public void setIdApoderado(Long idApoderado) {
		this.idApoderado = idApoderado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
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
	
	private static final long serialVersionUID = 2404278609081185478L;
}
