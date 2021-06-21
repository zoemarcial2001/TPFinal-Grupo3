package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		
		String pw = unTurista.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unTurista.setPassword(bCryptPasswordEncoder.encode(pw));
		
		int valorLatitud = (int) (Math.random()*30);
		int valorLongitud = (int) (Math.random()*30);
		unTurista.setLocalizacionLatitud(valorLatitud);
		unTurista.setLocalizacionLongitud(valorLongitud);
		unTurista.setTipo("consultor");
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
	public void modificarTurista(Turista turistaModificado) throws Exception {
	
		Turista turistaAModificar = turistaDAO.findById(turistaModificado.getId()).orElseThrow(()->new Exception("El turista no fue encontrado"));
		cambiarTurista(turistaModificado, turistaAModificar);
		turistaDAO.save(turistaAModificar);
		
	}

	@Override
	public void eliminarTurista(int id) throws Exception {
	
		Turista turistaEliminar = turistaDAO.findById(id).orElseThrow(()->new Exception("El turista no fue encontrado"));
		turistaDAO.delete(turistaEliminar);
	}
	
	

	private void cambiarTurista(Turista desde, Turista hacia) {
		hacia.setNombre(desde.getNombre());
		hacia.setApellido(desde.getApellido());
		hacia.setPais(desde.getPais());
		hacia.setPuntos(desde.getPuntos());
		hacia.setTipo(desde.getTipo());
	}

	@Override
	public Turista buscarUnTurista(String email) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findByEmail(email).orElseThrow(() ->new Exception("el turista no existe"));
	}

	@Override
	public List<Turista> conMasPuntos() {
		// TODO Auto-generated method stub
		return (List<Turista>) turistaDAO.conMasPuntos();
	}

	
}
