package br.com.felipemira.service;

import java.util.List;

import br.com.felipemira.dto.DiretorDTO;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public interface DiretorService {
	
	DiretorDTO create(DiretorDTO diretor);

    DiretorDTO delete(String id);
 
    List<DiretorDTO> findAll();

    DiretorDTO findById(String id);

    DiretorDTO update(DiretorDTO diretor);
}
