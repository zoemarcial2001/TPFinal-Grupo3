package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoI;


@Repository
public interface IPoIDAO extends CrudRepository<PoI, Integer>{

	@Query("from PoI p order by p.codigoPoI")
	public List<PoI> obtenerPoIs();
}
