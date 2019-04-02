package br.com.felipemira.actions;

import br.com.felipemira.cucumber.CucumberRoot;
import br.com.felipemira.cucumber.Requester;
import br.com.felipemira.cucumber.contants.ConstantPaths;
import br.com.felipemira.enums.AppHost;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@Component
public class ActionDiretor extends CucumberRoot {

    @Autowired
    @Getter@Setter
    private Requester requester;

    private JSONObject diretorPostJson;

    private Faker faker = new Faker();

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


}
