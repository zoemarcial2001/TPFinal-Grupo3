package ar.edu.unju.edm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.validator.validacionEmail;

@Entity
@Table (name="TURISTAS")
@Component
public class Turista {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer id;
	@NotEmpty (message = "debe ingresar un correo")
	@Column
    @validacionEmail
	private String email;
	@NotEmpty (message = "debe ingresar una contraseña")
	@Column
	private String password;
	@Pattern(regexp = "[A-Za-z]+", message="Solo puede tener letras")
	@Column
	private String nombre;
	@Pattern(regexp = "[A-Za-z]+", message="Solo puede tener letras")
	@Column
	private String apellido;
	@Column
	private String pais;
	@Column
	private int localizacionLatitud;
	@Column
	private int localizacionLongitud;
	@Column
	private int puntos;
	@Column
	private String tipo;
	@Lob
	@Column(name = "fotoPerfil", columnDefinition = "LONGBLOB")
	private String fotoPerfil;

	
	@OneToMany(mappedBy = "turista",cascade = CascadeType.ALL)
	private List<Turistas_PoIs> turistaValoracion;
	
	@OneToMany(mappedBy = "turistaAutor",cascade = CascadeType.ALL)
	private List<PoI> turistas;
	
	public Turista() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getLocalizacionLatitud() {
		return localizacionLatitud;
	}

	public void setLocalizacionLatitud(int localizacionLatitud) {
		this.localizacionLatitud = localizacionLatitud;
	}

	public int getLocalizacionLongitud() {
		return localizacionLongitud;
	}

	public void setLocalizacionLongitud(int localizacionLongitud) {
		this.localizacionLongitud = localizacionLongitud;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	
	

}
