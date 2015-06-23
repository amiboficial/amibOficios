package mx.amib.sistemas.external.oficios.revocacion;

import java.util.Date;
import java.util.List;

public class RevocacionTO {
	private Long id;
	private Long version;
	
	private Long idGrupoFinanciero;
	private Long idInstitucion;
	private Long idNotario;
	private Integer numeroEscritura;
	private String representanteLegalNombre;
	private String representanteLegalApellido1;
	private String representanteLegalApellido2;
	private Date fechaApoderamiento;
	private String uuidDocumentoRespaldo;
	
	private List<RevocadoTO> revocados;
	
	private Date fechaCreacion;
	private Date fechaModificacion;
}
