package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Fotografia;
import ar.edu.unju.edm.service.IFotografiaService;

@Service
@Qualifier("impsqlfot")
public class FotografiaServiceMySQL implements IFotografiaService{

	@Override
	public void guardarFotografia(Fotografia unaFotografia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fotografia crearFotografia() {
		// TODO Auto-generated method stub
		return null;
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
