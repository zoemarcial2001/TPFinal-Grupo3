package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;

@Service
public interface IPoIService {

	public void guardarPoI(PoI unPoI);
	public PoI crearPoI();
	public List<PoI> obtenerTodosPoIs();
	public PoI encontrarUnPoI(int codigoPoI) throws Exception;
	public void modificarPoI(PoI poIModificado) throws Exception;
	public void eliminarPoI(int codigoPoI) throws Exception ;
	public List<PoI> obtenerMisPoIs(Turista turistaAutor);
	public List<PoI> masValorados();
	public List<PoI> masComentados();
}
