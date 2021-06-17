package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Cupon;

@Repository
public interface ICuponDAO extends CrudRepository<Cupon, Integer>{
	
	@Query("from Cupon c order by c.idCupon")
	public List<Cupon> obtenerCupones();

}
