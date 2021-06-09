package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Fotografia;

@Repository
public interface IFotografiaDAO extends CrudRepository<Fotografia, Integer>{

}
