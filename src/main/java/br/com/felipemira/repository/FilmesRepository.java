package br.com.felipemira.repository;

import br.com.felipemira.model.Filme;
import br.com.felipemira.model.Filme;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public interface FilmesRepository extends Repository<Filme, String> {
	 
    void delete(Filme deleted);
    
    List<Filme> findAll();
 
    Optional<Filme> findById(String id);
 
    Filme save(Filme saved);
}
