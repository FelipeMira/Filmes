package br.com.felipemira.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.felipemira.dto.DiretorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipemira.error.NotFoundException;
import br.com.felipemira.service.DiretorService;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@RestController
@RequestMapping("/api/diretor")
public class DiretorController {
	
	private final DiretorService service;
	 
    @Autowired
    DiretorController(DiretorService service) {
        this.service = service;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    DiretorDTO create(@RequestBody @Valid DiretorDTO diretorEntry) {
        return service.create(diretorEntry);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    DiretorDTO delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
 
    @RequestMapping(method = RequestMethod.GET)
    List<DiretorDTO> findAll() {
        return service.findAll();
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    DiretorDTO findById(@PathVariable("id") String id) {
        return service.findById(id);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    DiretorDTO update(@PathVariable("id") String id, @RequestBody @Valid DiretorDTO diretorEntry) {
        diretorEntry.setId(id);
        return service.update(diretorEntry);
    }
 
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(NotFoundException ex) {
    }
}
