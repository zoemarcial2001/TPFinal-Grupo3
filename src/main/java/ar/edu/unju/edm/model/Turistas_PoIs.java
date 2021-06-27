package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="turistasPois")
public class Turistas_PoIs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idTuristas_PoIs;
	
	@ManyToOne
	@JoinColumn(name = "codPoI")
	private PoI poI;
	
	@ManyToOne
	@JoinColumn(name = "idTurista")
	private Turista turista;
	
	@Column
	private String comentario;
	
	@Column
	private int valoracion;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaComentario;
	
	
	public Turistas_PoIs() {
		// TODO Auto-generated constructor stub
	}

	public int getIdTuristas_PoIs() {
		return idTuristas_PoIs;
	}

	public void setIdTuristas_PoIs(int idTuristas_PoIs) {
		this.idTuristas_PoIs = idTuristas_PoIs;
	}

	public PoI getPoI() {
		return poI;
	}

	public void setPoI(PoI poI) {
		this.poI = poI;
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public LocalDate getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(LocalDate fechaComentario) {
		this.fechaComentario = fechaComentario;
	}
	
	
	
	
}
