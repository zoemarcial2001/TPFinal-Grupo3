package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="fotografias")
public class Fotografia {

	@Id
	@Column
	private int idFoto;
	
	@ManyToOne
	@JoinColumn(name = "codigoPoI")
	private PoI poI;
	
	@Column
	private String linkFoto;
	
	
	public Fotografia() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public PoI getPoI() {
		return poI;
	}

	public void setPoI(PoI poI) {
		this.poI = poI;
	}

	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}
	
	
}