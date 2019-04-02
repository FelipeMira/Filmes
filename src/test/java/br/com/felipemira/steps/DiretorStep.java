package br.com.felipemira.steps;

import br.com.felipemira.actions.ActionDiretor;
import br.com.felipemira.cucumber.CucumberRoot;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

public class DiretorStep extends CucumberRoot {

    @Autowired
    ActionDiretor actionDiretor;

    @Dado("^Que quero testar o retorno do diretror$")
    public void que_quero_testar_o_retorno_do_diretror() {
        actionDiretor.postDiretor();
    }

    @Quando("^O cliente chama a api de diretor$")
    public void o_cliente_chama_a_api_de_diretor() {
        actionDiretor.chamarTodosDiretores();
    }
}
