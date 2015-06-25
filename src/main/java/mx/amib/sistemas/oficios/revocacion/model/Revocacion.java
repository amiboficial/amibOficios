package mx.amib.sistemas.oficios.revocacion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="t103_t_revocacion")
public class Revocacion implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="version")
	private Long version;
	
	@Column(name="id_f_grupofinanciero")
	private Long idGrupoFinanciero;
	@Column(name="id_f_institucion")
	private Long idInstitucion;
	@Column(name="id_f_notario")
	private Long idNotario;
	@Column(name="nu_escritura")
	private Integer numeroEscritura;
	@Column(name="nb_nombrereplegal")
	private String representanteLegalNombre;
	@Column(name="nb_apellido1replegal")
	private String representanteLegalApellido1;
	@Column(name="nb_apellido2replegal")
	private String representanteLegalApellido2;
	@Column(name="fh_revocacion")
	private Date fechaRevocacion;
	@Column(name="uuid_f_docrespaldo")
	private String uuidDocumentoRespaldo;
	
	@OneToMany(mappedBy="revocacion", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true )
	private List<Revocado> revocados;
	
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
	
	public Long getIdGrupoFinanciero() {
		return idGrupoFinanciero;
	}
	public void setIdGrupoFinanciero(Long idGrupoFinanciero) {
		this.idGrupoFinanciero = idGrupoFinanciero;
	}
	public Long getIdInstitucion() {
		return idInstitucion;
	}
	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}
	public Long getIdNotario() {
		return idNotario;
	}
	public void setIdNotario(Long idNotario) {
		this.idNotario = idNotario;
	}
	public Integer getNumeroEscritura() {
		return numeroEscritura;
	}
	public void setNumeroEscritura(Integer numeroEscritura) {
		this.numeroEscritura = numeroEscritura;
	}
	public String getRepresentanteLegalNombre() {
		return representanteLegalNombre;
	}
	public void setRepresentanteLegalNombre(String representanteLegalNombre) {
		this.representanteLegalNombre = representanteLegalNombre;
	}
	public String getRepresentanteLegalApellido1() {
		return representanteLegalApellido1;
	}
	public void setRepresentanteLegalApellido1(String representanteLegalApellido1) {
		this.representanteLegalApellido1 = representanteLegalApellido1;
	}
	public String getRepresentanteLegalApellido2() {
		return representanteLegalApellido2;
	}
	public void setRepresentanteLegalApellido2(String representanteLegalApellido2) {
		this.representanteLegalApellido2 = representanteLegalApellido2;
	}
	public Date getFechaRevocacion() {
		return fechaRevocacion;
	}
	public void setFechaRevocacion(Date fechaRevocacion) {
		this.fechaRevocacion = fechaRevocacion;
	}
	public String getUuidDocumentoRespaldo() {
		return uuidDocumentoRespaldo;
	}
	public void setUuidDocumentoRespaldo(String uuidDocumentoRespaldo) {
		this.uuidDocumentoRespaldo = uuidDocumentoRespaldo;
	}
	public List<Revocado> getRevocados() {
		return revocados;
	}
	public void setRevocados(List<Revocado> revocados) {
		this.revocados = revocados;
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
	
	
	private static final long serialVersionUID = 9182514063608328911L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
