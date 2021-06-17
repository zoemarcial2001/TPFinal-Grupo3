package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cupon;

@Service
public interface ICuponService {

	public void guardarCupon(Cupon unCupon);
	public Cupon crearCupon();
	public List<Cupon> obtenerTodosCupones();
	public void modificarCupon(Cupon cuponModificado) throws Exception;
	public void eliminarCupon(int idCupon) throws Exception ;
	
}
