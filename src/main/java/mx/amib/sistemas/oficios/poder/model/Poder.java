package mx.amib.sistemas.oficios.poder.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="t101_t_poder")
public class Poder implements Serializable {
	
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
	@Column(name="fh_apoderamiento")
	private Date fechaApoderamiento;
	@Column(name="uuid_f_docrespaldo")
	private String uuidDocumentoRespaldo;
	
	@OneToMany(mappedBy="poder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true )
	private List<Apoderado> apoderados;
	
	@Column(name="fh_creacion")
	private Date fechaCreacion;
	@Column(name="fh_modificacion")
	private Date fechaModificacion;
	
	@Column(name="tx_ultusuariomod")
	private String usuarioMod;
	
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
	public Date getFechaApoderamiento() {
		return fechaApoderamiento;
	}
	public void setFechaApoderamiento(Date fechaApoderamiento) {
		this.fechaApoderamiento = fechaApoderamiento;
	}
	public String getUuidDocumentoRespaldo() {
		return uuidDocumentoRespaldo;
	}
	public void setUuidDocumentoRespaldo(String uuidDocumentoRespaldo) {
		this.uuidDocumentoRespaldo = uuidDocumentoRespaldo;
	}
	public List<Apoderado> getApoderados() {
		return apoderados;
	}
	public void setApoderados(List<Apoderado> apoderados) {
		this.apoderados = apoderados;
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
	public String getUsuarioMod() {
		return usuarioMod;
	}
	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}


	private static final long serialVersionUID = 1L;
}
