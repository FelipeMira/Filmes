package br.com.felipemira.actions;

import br.com.felipemira.cucumber.CucumberRoot;
import br.com.felipemira.cucumber.Requester;
import br.com.felipemira.cucumber.contants.ConstantPaths;
import br.com.felipemira.enums.AppHost;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@Component
public class ActionGoogle extends CucumberRoot {

    @Autowired
    @Getter
    @Setter
    private Requester requester;

    @Getter@Setter
    private String parametroPesquisa;

    public void informarPesquisa() throws JSONException {
        requester.getApi(AppHost.Local, ConstantPaths.PATH_DIRETOR_E_FILME, true, "Consultar diretor e Filme!");

        JSONArray diretoresEFilmes = new JSONArray(Objects.requireNonNull(getResponse().getBody().toString()));

        JSONObject diretorEFilme = new JSONObject(diretoresEFilmes.get(0).toString());

        JSONObject diretor = new JSONObject(diretorEFilme.get("diretor").toString());

        JSONObject filme = new JSONObject(diretorEFilme.get("filme").toString());

        parametroPesquisa = filme.getString("nome") + " " + diretor.getString("nome");

        googlePage.getCampoPesquisa().sendKeys(parametroPesquisa);
    }

    public void pesquisar() {
        googlePage.getBtnPesquisa().click();
    }

    public void validarRetornoPesquisaGoogle() {
        Long qtdResultadoPesquisa = Long.valueOf(String.valueOf(googlePage.getResultadoPesquisa().getText().split(" ")[1]).replaceAll(Pattern.quote ("."), ""));

        System.out.println("=====> QUANTIDADE DE REGISTROS RETORNADOS NA PESQUISA: " + qtdResultadoPesquisa + " <=====");
        assert(qtdResultadoPesquisa > 0);
    }
}
