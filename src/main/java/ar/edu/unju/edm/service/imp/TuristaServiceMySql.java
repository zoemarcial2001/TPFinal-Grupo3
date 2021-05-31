package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.service.ITuristaService;

@Service
@Qualifier("impsql")
public class TuristaServiceMySql implements ITuristaService{
	
	@Autowired
	Turista unTurista;
	
	@Autowired
	ITuristaDAO turistaDAO;
	
	@Override
	public void guardarTurista(Turista unTurista) {
		
		turistaDAO.save(unTurista);
		
	}

	@Override
	public Turista crearTurista() {
		
		return unTurista;
		
	}

	@Override
	public List<Turista> obtenerTodosTuristas() {
		
		return (List<Turista>) turistaDAO.findAll();
		
	}

	@Override
	public Turista encontrarUnTurista(int id) throws Exception {
		
		return turistaDAO.findById(id).orElseThrow(() ->new Exception("el turista no existe"));
		
	}

	@Override
	public void modificarTurista(Turista unTuristaModificado) throws Exception {
	
		Turista turistaAModificar = turistaDAO.findById(unTuristaModificado.getId()).orElseThrow(()->new Exception("El turista no fue encontrado"));
		cambiarTurista(unTuristaModificado, turistaAModificar);
		turistaDAO.save(turistaAModificar);
		
	}

	@Override
	public void eliminarTurista(int id) throws Exception {
	
		Turista turistaEliminar = turistaDAO.findById(id).orElseThrow(()->new Exception("El turista no fue encontrado"));
		turistaDAO.delete(turistaEliminar);
	}

	private void cambiarTurista(Turista desde, Turista hacia) {
		hacia.setEmail(desde.getEmail());
		hacia.setNombre(desde.getNombre());
		hacia.setPais(desde.getPais());
		hacia.setEmail(desde.getEmail());
	}
	
}