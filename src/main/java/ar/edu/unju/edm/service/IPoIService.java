package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;

@Service
public interface IPoIService {

	public void guardarPoI(PoI unPoI);
	public PoI crearPoI();
	public List<PoI> obtenerTodosPoIs();
	public PoI encontrarUnPoI(int codigoPoI) throws Exception;
	public void modificarPoI(PoI poIModificado) throws Exception;
	public void eliminarPoI(int codigoPoI) throws Exception ;
}
