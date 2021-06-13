package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Fotografia;
import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.repository.IFotografiaDAO;
import ar.edu.unju.edm.service.IFotografiaService;

@Service
@Qualifier("impsqlfot")
public class FotografiaServiceMySQL implements IFotografiaService{

	@Autowired
	Fotografia unaFotografia;
	
	@Autowired
	IFotografiaDAO fotografiaDAO;
	
	@Override
	public void guardarFotografia(Fotografia unaFotografia) {
		// TODO Auto-generated method stub
		fotografiaDAO.save(unaFotografia);
	}

	@Override
	public Fotografia crearFotografia() {
		// TODO Auto-generated method stub
		return unaFotografia;
	}

	@Override
	public void modificarFotografia(Fotografia fotografiaModificada) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarFotografia(int idFoto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fotografia> obtenerTodasFotografias() {
		// TODO Auto-generated method stub
		return null;
	}

}
