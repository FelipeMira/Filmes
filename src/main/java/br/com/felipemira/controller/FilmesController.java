package br.com.felipemira.controller;

import br.com.felipemira.dto.FilmeDTO;
import br.com.felipemira.error.NotFoundException;
import br.com.felipemira.service.FilmesService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@RestController
@RequestMapping("/api/filmes")
public class FilmesController {

	private final FilmesService service;

    @Autowired
    FilmesController(FilmesService service) {
        this.service = service;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    FilmeDTO create(@RequestBody @Valid FilmeDTO filmeEntry) {
        return service.create(filmeEntry);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    FilmeDTO delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
 
    @RequestMapping(method = RequestMethod.GET)
    List<FilmeDTO> findAll() {
        return service.findAll();
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    FilmeDTO findById(@PathVariable("id") String id) {
        return service.findById(id);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    FilmeDTO update(@PathVariable("id") String id, @RequestBody @Valid FilmeDTO filmeEntry) {
        filmeEntry.setId(id);
        return service.update(filmeEntry);
    }
 
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(NotFoundException ex) {
    }
}
