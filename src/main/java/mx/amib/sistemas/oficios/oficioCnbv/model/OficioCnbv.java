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
	
	@Column(name="tx_dga")
	private String claveDga;
	@Column(name="fh_inicio")
	private Date fechaInicioVigencia;
	@Column(name="uuid_f_docrespaldo")
	private String uuidDocumentoRespaldo;
	
	//TODO: Mapeo one-to-many bidereccional
	@OneToMany(mappedBy="oficioCnbv", fetch = FetchType.EAGER)
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
	
	public String getClaveDga() {
		return claveDga;
	}
	public void setClaveDga(String claveDga) {
		this.claveDga = claveDga;
	}
	public Date getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public void setFechaInicioVigencia(Date fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
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
