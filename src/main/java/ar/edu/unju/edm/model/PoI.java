package ar.edu.unju.edm.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table (name="POIS")
@Component
public class PoI {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native") 
	@Column
	private int codigoPoI;
	
	@NotBlank(message="debe ingresar un nombre")
	@Column
	private String nombrePoI;
	@Column
	private String descripcion;
	@Column
	private String etiqueta;
	@Column
	private String sitioWeb;
	@NotBlank(message="debe ingresar una calle")
	@Column
	private String calle;
	@NotNull(message="debe ingresar un numero")
	@Column
	private int numeroCasa;
	@NotBlank(message="debe ingresar un barrio")
	@Column
	private String barrio;
	@NotBlank(message="debe ingresar una localidad")
	@Column
	private String localidad;
	@Column
	private int latitud;
	@Column
	private int longitud;
	
	@ManyToOne
	@JoinColumn(name = "emailTurista")
	private Turista turistaAutor;
	
	@OneToMany(mappedBy = "poI")
	private List<Fotografia> fotografias;
	
	
	public PoI() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getCodigoPoI() {
		return codigoPoI;
	}
	public void setCodigoPoI(int codigoPoI) {
		this.codigoPoI = codigoPoI;
	}
	public String getNombrePoI() {
		return nombrePoI;
	}
	public void setNombrePoI(String nombrePoI) {
		this.nombrePoI = nombrePoI;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getSitioWeb() {
		return sitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public int getLatitud() {
		return latitud;
	}
	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public Turista getTuristaAutor() {
		return turistaAutor;
	}


	public void setTuristaAutor(Turista turistaAutor) {
		this.turistaAutor = turistaAutor;
	}


	public List<Fotografia> getFotografias() {
		return fotografias;
	}


	public void setFotografias(List<Fotografia> fotografias) {
		this.fotografias = fotografias;
	}
	
}
