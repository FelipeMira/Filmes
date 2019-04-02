package br.com.felipemira.mongoDb;

import br.com.felipemira.dto.FilmeDTO;
import br.com.felipemira.error.NotFoundException;
import br.com.felipemira.model.Filme;
import br.com.felipemira.repository.FilmesRepository;
import br.com.felipemira.service.FilmesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@Service
public final class MongoDBFilmesService implements FilmesService {

	private final FilmesRepository repository;

    @Autowired
    MongoDBFilmesService(FilmesRepository repository) {
        this.repository = repository;
    }
 
    @Override
    public FilmeDTO create(FilmeDTO filme) {
        Filme persisted = Filme.getBuilder()
                .nome(filme.getNome())
                .lancamento(filme.getLancamento())
                .build();
        persisted = repository.save(persisted);
        return convertToDTO(persisted);
    }
 
    @Override
    public FilmeDTO delete(String id) {
        Filme deleted = findfilmeById(id);
        repository.delete(deleted);
        return convertToDTO(deleted);
    }
 
    @Override
    public List<FilmeDTO> findAll() {
        List<Filme> filmeEntries = repository.findAll();
        return convertToDTOs(filmeEntries);
    }
 
    private List<FilmeDTO> convertToDTOs(List<Filme> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }
 
    @Override
    public FilmeDTO findById(String id) {
        Filme found = findfilmeById(id);
        return convertToDTO(found);
    }
 
    @Override
    public FilmeDTO update(FilmeDTO filme) {
        Filme updated = findfilmeById(filme.getId());
        updated.update(filme.getNome(), filme.getLancamento());
        updated = repository.save(updated);
        return convertToDTO(updated);
    }
 
    private Filme findfilmeById(String id) {
        Optional<Filme> result = repository.findById(id);
        return result.orElseThrow(() -> new NotFoundException(id));
 
    }
 
    private FilmeDTO convertToDTO(Filme model) {
        FilmeDTO dto = new FilmeDTO();
 
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setLancamento(model.getLancamento());
 
        return dto;
    }
}
