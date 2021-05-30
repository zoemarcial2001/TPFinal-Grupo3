package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pol;


@Service
public interface IPolService {

	public void guardarPol(Pol unPol);
	public Pol crearPol();
	public List<Pol> obtenerTodosPols();
	public Pol encontrarUnPol(int codigoPol) throws Exception;
	public void modificarPol(Pol polModificado) throws Exception;
	public void eliminarPol(int codigoPol) throws Exception ;
}
