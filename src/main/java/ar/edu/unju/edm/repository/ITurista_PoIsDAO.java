package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Turistas_PoIs;

@Repository
public interface ITurista_PoIsDAO extends CrudRepository<Turistas_PoIs, Integer>{

}
