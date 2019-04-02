package br.com.felipemira.actions;

import br.com.felipemira.cucumber.CucumberRoot;
import br.com.felipemira.cucumber.Requester;
import br.com.felipemira.cucumber.contants.ConstantPaths;
import br.com.felipemira.enums.AppHost;
import br.com.felipemira.ui.automator.utils.core.Selenium;
import br.com.felipemira.ui.automator.utils.po.GooglePage;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@Component
public class ActionDiretor extends CucumberRoot {

    @Autowired
    @Getter@Setter
    private Requester requester;

    @Autowired
    @Getter@Setter
    private Selenium selenium;

    private JSONObject diretorPostJson;

    private Faker faker = new Faker();

    @Getter@Setter
    private String parametroPesquisa;

    GooglePage googlePage;

    /**
     * Cria um diretor aleatorio com a data de nascimento fixa
     */
    private void criarDiretor(){
        diretorPostJson = new JSONObject();
        diretorPostJson.put("nascimento", "1990-01-25");
        diretorPostJson.put("nome", faker.name().firstName() + " " + faker.name().lastName());
    }

    /**
     * Faz a chamada na api de diretores
     */
    public void postDiretor() {
        criarDiretor();
        requester.postApi(AppHost.Local, ConstantPaths.PATH_DIRETOR, diretorPostJson, true, "Salvar Diretor!");
    }

    public void chamarTodosDiretores() {
        requester.getApi(AppHost.Local, ConstantPaths.PATH_DIRETOR, true, "Consultar diretores!");
    }

    public void prepararWebDriverGoogle() {
        selenium.getDriver();

        Selenium.driver.get("http://www.google.com.br");
        // setting browser window size
        Selenium.driver.manage().window().maximize();

        googlePage = new GooglePage();
    }

    public void informarPesquisa() {
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
