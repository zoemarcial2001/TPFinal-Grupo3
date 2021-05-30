package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.repository.IPolDAO;
import ar.edu.unju.edm.service.IPolService;

@Service
@Qualifier("impsql")
public class PolServiceMySQL implements IPolService {

	@Autowired
	Pol unPol;
	
	@Autowired
	IPolDAO polDAO;
	
	@Override
	public void guardarPol(Pol unPol) {
		// TODO Auto-generated method stub
		polDAO.save(unPol);
	}

	@Override
	public Pol crearPol() {
		// TODO Auto-generated method stub
		return unPol;
	}

	@Override
	public List<Pol> obtenerTodosPols() {
		// TODO Auto-generated method stub
		return (List<Pol>) polDAO.findAll();
	}

	@Override
	public Pol encontrarUnPol(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return polDAO.findById(codigo).orElseThrow(()->new Exception("el pol no existe"));
	}

	@Override
	public void modificarPol(Pol polModificado) throws Exception {
		// TODO Auto-generated method stub
		Pol polAModificar = polDAO.findById(polModificado.getCodigoPol()).orElseThrow(()->new Exception("El Pol no fue encontrado"));
		cambiarPol(polModificado, polAModificar);
		polDAO.save(polAModificar);
	}
	
	private void cambiarPol(Pol desde, Pol hacia) {
		hacia.setDescripcion(desde.getDescripcion());
		hacia.setEmail(desde.getEmail());
		hacia.setEtiqueta(desde.getEtiqueta());
		hacia.setNombre(desde.getNombre());
		hacia.setBarrio(desde.getBarrio());
		hacia.setCalle(desde.getCalle());
		hacia.setNumeroCasa(desde.getNumeroCasa());
	}

	@Override
	public void eliminarPol(int codigo) throws Exception {
		// TODO Auto-generated method stub
		Pol polEliminar = polDAO.findById(codigo).orElseThrow(()->new Exception("el producto no fue encontrado"));
		polDAO.delete(polEliminar);
	}

}
