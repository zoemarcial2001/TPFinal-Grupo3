package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_PoIs;

@Service
public interface ITurista_PoIsService {
	
	public void guardarValoracion(Turistas_PoIs unaValoracion);
	public Turistas_PoIs crearValoracion();
	public List<Turistas_PoIs> obtenerTodasValoraciones();
	public Turistas_PoIs encontrarUnaValoracion(int idTuristas_PoIs) throws Exception;
	public void modificarValoracion(Turistas_PoIs valoracionModificada) throws Exception;
	public void eliminarValoracion(int idTuristas_PoIs) throws Exception;
    public List<Turistas_PoIs> obtenerTodasValoracionesUnPois(PoI poIValorado);
	List<Turistas_PoIs> obtenerTodasValoracionesUnTurista(Turista turistaBuscado);

}
