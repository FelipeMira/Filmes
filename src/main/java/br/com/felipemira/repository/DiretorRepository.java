package br.com.felipemira.repository;

import java.util.List;
import java.util.Optional;

import br.com.felipemira.model.Diretor;
import org.springframework.data.repository.Repository;

public interface DiretorRepository extends Repository<Diretor, String> {
	 
    void delete(Diretor deleted);
    
    List<Diretor> findAll();
 
    Optional<Diretor> findById(String id);
 
    Diretor save(Diretor saved);
}
