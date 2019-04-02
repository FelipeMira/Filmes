package br.com.felipemira.steps;

import br.com.felipemira.actions.ActionDiretor;
import br.com.felipemira.cucumber.CucumberRoot;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.felipemira.ui.automator.utils.core.Selenium.resetDriver;

public class DiretorStep extends CucumberRoot {

    @Autowired
    ActionDiretor actionDiretor;

    @Dado("^Que quero testar o retorno do diretror$")
    public void que_quero_testar_o_retorno_do_diretror() {
        actionDiretor.postDiretor();
    }

    @Dado("^Que eu quero testar uma pesquisa no google$")
    public void queEuQueroTestarUmaPesquisaNoGoogle() {
        actionDiretor.prepararWebDriverGoogle();
    }

    @Quando("^O cliente chama a api de diretor$")
    public void o_cliente_chama_a_api_de_diretor() {
        actionDiretor.chamarTodosDiretores();
    }



    @Quando("^Informo o parametro da pesquisa$")
    public void informoOParametroDaPesquisa() {
        actionDiretor.informarPesquisa();
    }

    @E("^Clico em Pesquisar$")
    public void clicoEmPesquisar() {
        actionDiretor.pesquisar();
    }

    @Entao("^Valido quantos resultados foram obtidos$")
    public void validoQuantosResultadosForamObtidos() {
        actionDiretor.validarRetornoPesquisaGoogle();
        resetDriver();
    }
}
