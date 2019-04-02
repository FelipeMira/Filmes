package br.com.felipemira.service;

import br.com.felipemira.dto.FilmeDTO;

import java.util.List;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public interface FilmesService {
	
	FilmeDTO create(FilmeDTO filme);

    FilmeDTO delete(String id);
 
    List<FilmeDTO> findAll();

    FilmeDTO findById(String id);

    FilmeDTO update(FilmeDTO filme);
}
