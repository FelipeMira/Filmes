package br.com.felipemira.mongoDb;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import br.com.felipemira.dto.DiretorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipemira.error.NotFoundException;
import br.com.felipemira.model.Diretor;
import br.com.felipemira.repository.DiretorRepository;
import br.com.felipemira.service.DiretorService;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@Service
public final class MongoDBDiretorService implements DiretorService {
	
	private final DiretorRepository repository;
	 
    @Autowired
    MongoDBDiretorService(DiretorRepository repository) {
        this.repository = repository;
    }
 
    @Override
    public DiretorDTO create(DiretorDTO diretor) {
        Diretor persisted = Diretor.getBuilder()
                .nome(diretor.getNome())
                .nascimento(diretor.getNascimento())
                .build();
        persisted = repository.save(persisted);
        return convertToDTO(persisted);
    }
 
    @Override
    public DiretorDTO delete(String id) {
        Diretor deleted = finddiretorById(id);
        repository.delete(deleted);
        return convertToDTO(deleted);
    }
 
    @Override
    public List<DiretorDTO> findAll() {
        List<Diretor> diretorEntries = repository.findAll();
        return convertToDTOs(diretorEntries);
    }
 
    private List<DiretorDTO> convertToDTOs(List<Diretor> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }
 
    @Override
    public DiretorDTO findById(String id) {
        Diretor found = finddiretorById(id);
        return convertToDTO(found);
    }
 
    @Override
    public DiretorDTO update(DiretorDTO diretor) {
        Diretor updated = finddiretorById(diretor.getId());
        updated.update(diretor.getNome(), diretor.getNascimento());
        updated = repository.save(updated);
        return convertToDTO(updated);
    }
 
    private Diretor finddiretorById(String id) {
        Optional<Diretor> result = repository.findById(id);
        return result.orElseThrow(() -> new NotFoundException(id));
 
    }
 
    private DiretorDTO convertToDTO(Diretor model) {
        DiretorDTO dto = new DiretorDTO();
 
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setNascimento(model.getNascimento());
 
        return dto;
    }
}
