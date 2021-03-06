package mx.amib.sistemas.oficios.oficioCnbv.model;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OficioCnbv
 *
 */
@Entity
@Table(name="t105_t_oficiocnbv")
public class OficioCnbv implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="version")
	private Long version;
	
	@Column(name="tx_dga")
	private String claveDga;
	@Column(name="nu_oficio")
	private Integer numeroOficio;
	@Column(name="fh_oficio")
	private Date fechaOficio;
	@Column(name="uuid_f_docrespaldo")
	private String uuidDocumentoRespaldo;
	@OneToMany(mappedBy="oficioCnbv", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true )
	private List<AutorizadoCnbv> autorizados;
	
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public String getClaveDga() {
		return claveDga;
	}
	public void setClaveDga(String claveDga) {
		this.claveDga = claveDga;
	}
	public Integer getNumeroOficio() {
		return numeroOficio;
	}
	public void setNumeroOficio(Integer numeroOficio) {
		this.numeroOficio = numeroOficio;
	}
	public Date getFechaOficio() {
		return fechaOficio;
	}
	public void setFechaOficio(Date fechaOficio) {
		this.fechaOficio = fechaOficio;
	}
	public String getUuidDocumentoRespaldo() {
		return uuidDocumentoRespaldo;
	}
	public void setUuidDocumentoRespaldo(String uuidDocumentoRespaldo) {
		this.uuidDocumentoRespaldo = uuidDocumentoRespaldo;
	}
	public List<AutorizadoCnbv> getAutorizados() {
		return autorizados;
	}
	public void setAutorizados(List<AutorizadoCnbv> autorizados) {
		this.autorizados = autorizados;
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
	
	private static final long serialVersionUID = 147857L;
}
