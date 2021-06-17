package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cupon;
import ar.edu.unju.edm.repository.ICuponDAO;
import ar.edu.unju.edm.service.ICuponService;

@Service
@Qualifier("impsqlcupon")
public class CuponServiceMySQL implements ICuponService{

	
	@Autowired
	Cupon unCupon;
	
	@Autowired
	ICuponDAO cuponDAO;
	
	
	@Override
	public void guardarCupon(Cupon unCupon) {
		// TODO Auto-generated method stub
		cuponDAO.save(unCupon);
	}

	@Override
	public Cupon crearCupon() {
		// TODO Auto-generated method stub
		return unCupon;
	}

	@Override
	public List<Cupon> obtenerTodosCupones() {
		// TODO Auto-generated method stub
		return (List<Cupon>) cuponDAO.findAll();
	}

	@Override
	public void modificarCupon(Cupon cuponModificado) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCupon(int idCupon) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
