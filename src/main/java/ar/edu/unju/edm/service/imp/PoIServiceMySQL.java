package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.repository.IPoIDAO;
import ar.edu.unju.edm.service.IPoIService;

@Service
@Qualifier("impsql")
public class PoIServiceMySQL implements IPoIService {

	@Autowired
	PoI unPoI;
	
	@Autowired
	IPoIDAO poIDAO;
	
	@Override
	public void guardarPoI(PoI unPoI) {
		// TODO Auto-generated method stub
		poIDAO.save(unPoI);
	}

	@Override
	public PoI crearPoI() {
		// TODO Auto-generated method stub
		return unPoI;
	}

	@Override
	public List<PoI> obtenerTodosPoIs() {
		// TODO Auto-generated method stub
		return (List<PoI>) poIDAO.findAll();
	}

	@Override
	public PoI encontrarUnPoI(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return poIDAO.findById(codigo).orElseThrow(()->new Exception("el poI no existe"));
	}

	@Override
	public void modificarPoI(PoI poIModificado) throws Exception {
		// TODO Auto-generated method stub
		PoI poIAModificar = poIDAO.findById(poIModificado.getCodigoPoI()).orElseThrow(()->new Exception("El PoI no fue encontrado"));
		cambiarPoI(poIModificado, poIAModificar);
		poIDAO.save(poIAModificar);
	}
	
	private void cambiarPoI(PoI desde, PoI hacia) {
		hacia.setDescripcion(desde.getDescripcion());
		hacia.setEmail(desde.getEmail());
		hacia.setEtiqueta(desde.getEtiqueta());
		hacia.setNombre(desde.getNombre());
		hacia.setBarrio(desde.getBarrio());
		hacia.setCalle(desde.getCalle());
		hacia.setNumeroCasa(desde.getNumeroCasa());
	}

	@Override
	public void eliminarPoI(int codigo) throws Exception {
		// TODO Auto-generated method stub
		PoI poIEliminar = poIDAO.findById(codigo).orElseThrow(()->new Exception("el poI no fue encontrado"));
		poIDAO.delete(poIEliminar);
	}

}