package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_PoIs;
import ar.edu.unju.edm.repository.ITurista_PoIsDAO;
import ar.edu.unju.edm.service.ITurista_PoIsService;

@Service
@Qualifier("implementacionsql")
public class Turistas_PoIsServiceMySql implements ITurista_PoIsService{
	
	@Autowired
	Turistas_PoIs unavaloracion;
	
	@Autowired
	ITurista_PoIsDAO valoracionDAO;
	
	

	@Override
	public void guardarValoracion(Turistas_PoIs unaValoracion) {
		LocalDate fecha = LocalDate.now();
		unaValoracion.setFechaComentario(fecha);
		
		valoracionDAO.save(unaValoracion);
	}

	@Override
	public Turistas_PoIs crearValoracion() {
		// TODO Auto-generated method stub
		return unavaloracion;
	}

	@Override
	public List<Turistas_PoIs> obtenerTodasValoraciones() {
		// TODO Auto-generated method stub
		return (List<Turistas_PoIs>) valoracionDAO.findAll(); 
	}

	@Override
	public Turistas_PoIs encontrarUnaValoracion(int idTuristas_PoIs) throws Exception {
		// TODO Auto-generated method stub
		return valoracionDAO.findById(idTuristas_PoIs).orElseThrow(() ->new Exception("la valoracion no existe"));
	}

	@Override
	public void modificarValoracion(Turistas_PoIs valoracionModificada) throws Exception {
		// TODO Auto-generated method stub
		Turistas_PoIs valoracionAModificar = valoracionDAO.findById(valoracionModificada.getIdTuristas_PoIs()).orElseThrow(()->new Exception("El turista no fue encontrado"));
	    cambiarValoracion(valoracionModificada, valoracionAModificar);
	    valoracionDAO.save(valoracionAModificar);
	}
	
	private void cambiarValoracion (Turistas_PoIs desde, Turistas_PoIs hacia) {
		hacia.setComentario(desde.getComentario());
		hacia.setValoracion(desde.getValoracion());
	}

	@Override
	public void eliminarValoracion(int id) throws Exception {
		// TODO Auto-generated method stub
		Turistas_PoIs valoracionEliminar = valoracionDAO.findById(id).orElseThrow(()->new Exception("La valoracion no fue encontrada"));
	    valoracionDAO.delete(valoracionEliminar);
	}

	@Override
	public List<Turistas_PoIs> obtenerTodasValoracionesUnPois(PoI poIValorado) {
		// TODO Auto-generated method stub
		return (List<Turistas_PoIs>) valoracionDAO.findAllByPoI(poIValorado);
	}
	
	@Override
	public List<Turistas_PoIs> obtenerTodasValoracionesUnTurista(Turista turistaBuscado) {
		// TODO Auto-generated method stub
		return (List<Turistas_PoIs>) valoracionDAO.findAllByTurista(turistaBuscado);
	}

}
