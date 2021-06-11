package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;


@Repository
public interface IPoIDAO extends CrudRepository<PoI, Integer>{

	@Query("from PoI p order by p.codigoPoI")
	public List<PoI> obtenerPoIs();
	
	public Optional<PoI> findAllByTuristaAutor(Turista turistaAutor);
}
