package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Pol;


@Repository
public interface IPolDAO extends CrudRepository<Pol, Integer>{

	@Query("from Pol p order by p.codigoPol")
	public List<Pol> obtenerPols();
}
