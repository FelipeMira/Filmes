package br.com.felipemira.steps;

import br.com.felipemira.actions.ActionGoogle;
import br.com.felipemira.cucumber.CucumberRoot;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

public class GoogleSteps extends CucumberRoot {

    @Autowired
    ActionGoogle actionGoogle;

    @Quando("^Informo o parametro da pesquisa$")
    public void informoOParametroDaPesquisa() {
        actionGoogle.informarPesquisa();
    }

    @E("^Clico em Pesquisar$")
    public void clicoEmPesquisar() {
        actionGoogle.pesquisar();
    }

    @Entao("^Valido quantos resultados foram obtidos$")
    public void validoQuantosResultadosForamObtidos() {
        actionGoogle.validarRetornoPesquisaGoogle();
    }
}
