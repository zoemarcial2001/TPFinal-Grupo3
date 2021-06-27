package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_PoIs;

@Repository
public interface ITurista_PoIsDAO extends CrudRepository<Turistas_PoIs, Integer>{

	public List<Turistas_PoIs> findAllByPoI(PoI poIValorado);
	public List<Turistas_PoIs> findAllByTurista(Turista turistaBuscado);

		
}


