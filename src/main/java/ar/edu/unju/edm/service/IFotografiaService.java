package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Fotografia;


@Service
public interface IFotografiaService {

	public void guardarFotografia(Fotografia unaFotografia);
	public Fotografia crearFotografia();
	public List<Fotografia> obtenerTodasFotografias();
	public void modificarFotografia(Fotografia fotografiaModificada) throws Exception;
	public void eliminarFotografia(int idFoto) throws Exception ;
}
