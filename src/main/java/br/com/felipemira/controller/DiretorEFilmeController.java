package br.com.felipemira.controller;

import br.com.felipemira.model.Diretor;
import br.com.felipemira.model.DiretorEFilme;
import br.com.felipemira.model.Filme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@RestController
@RequestMapping("/api/diretorefilme")
public class DiretorEFilmeController {

    /**
     * Apenas para usar na automacao
     * @return ArrayList<DiretorEFilme>
     */
    @RequestMapping(method = RequestMethod.GET)
    ArrayList<DiretorEFilme> findDiretorEFilme() {
        ArrayList<DiretorEFilme> diretoresEFilmes = new ArrayList<>();

        DiretorEFilme diretorEFilme = new DiretorEFilme();

        Filme filme = new Filme();
        filme.update("Vingadores: Era de Ultron", LocalDate.of(2015, 4, 23));

        Diretor diretor = new Diretor();

        diretor.update("Joseph Hill Whedon", LocalDate.of(1964, 6, 23));

        diretorEFilme.setFilme(filme);
        diretorEFilme.setDiretor(diretor);

        diretoresEFilmes.add(diretorEFilme);

        return diretoresEFilmes;
    }
}
