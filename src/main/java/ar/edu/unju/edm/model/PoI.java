package ar.edu.unju.edm.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table (name="POIS")
@Component
public class PoI {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column
	private int codigoPoI;
	
	@Column
	@NotEmpty(message="El nombre del producto es obligatorio")
	private String nombrePoI;
	@Column
	private String descripcion;
	@Column
	private String etiqueta;
	@Column
	private String sitioWeb;
	@Column
	@NotEmpty(message="debe ingresar una calle")
	private String calle;
	@Column
	@NotNull(message="debe ingresar un numero")
	@Min(1)
	@Max(99999)
	private int numeroCasa;
	@Column
	@NotEmpty(message="debe ingresar un barrio")
	private String barrio;
	@Column
	@NotEmpty(message="debe ingresar una localidad")
	private String localidad;
	@Column
	private int latitud;
	@Column
	private int longitud;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCarga;
	@Lob
	@Column(name = "imagen", columnDefinition = "LONGBLOB")
	private String imagen;
	@Lob
	@Column(name = "imagen2", columnDefinition = "LONGBLOB")
	private String imagen2;
	@Lob
	@Column(name = "imagen3", columnDefinition = "LONGBLOB")
	private String imagen3;
	
	@ManyToOne
	@JoinColumn(name = "idTurista")
	private Turista turistaAutor;
	
	
	@OneToMany(mappedBy = "poI",cascade = CascadeType.ALL)
	private List<Turistas_PoIs> poiValoracion;
	
	
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

	
	public LocalDate getFechaCarga() {
		return fechaCarga;
	}


	public void setFechaCarga(LocalDate fechaCarga) {
		this.fechaCarga = fechaCarga;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getImagen2() {
		return imagen2;
	}


	public void setImagen2(String imagen2) {
		this.imagen2 = imagen2;
	}


	public String getImagen3() {
		return imagen3;
	}


	public void setImagen3(String imagen3) {
		this.imagen3 = imagen3;
	}


	public Turista getTuristaAutor() {
		return turistaAutor;
	}


	public void setTuristaAutor(Turista turistaAutor) {
		this.turistaAutor = turistaAutor;
	}

}
