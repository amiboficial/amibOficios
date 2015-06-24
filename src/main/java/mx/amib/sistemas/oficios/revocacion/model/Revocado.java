package mx.amib.sistemas.oficios.revocacion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import mx.amib.sistemas.oficios.poder.model.Apoderado;

@Entity
@Table(name="t104_t_revocado")
public class Revocado implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_105_revocacion")
	private Revocacion revocacion;
	@OneToOne
	@JoinColumn(name="id_102_apoderado")
	private Apoderado apoderado;
	
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
	public Revocacion getRevocacion() {
		return revocacion;
	}
	public void setRevocacion(Revocacion revocacion) {
		this.revocacion = revocacion;
	}
	public Apoderado getApoderado() {
		return apoderado;
	}
	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
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
