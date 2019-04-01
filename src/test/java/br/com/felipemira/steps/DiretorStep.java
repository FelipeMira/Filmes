package br.com.felipemira.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import br.com.felipemira.cucumber.CucumberRoot;
import br.com.felipemira.model.Diretor;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import util.DateFormatUtils;

public class DiretorStep extends CucumberRoot {
	
	private ResponseEntity<String> response;

	private Diretor[] responseObject;

    @Dado("^Que quero testar o retorno do diretror$")
    public void que_quero_testar_o_retorno_do_diretror() {
        Diretor diretor = new Diretor();
        Date date = DateFormatUtils.fromyyyyMMddToDate("1990-01-25");
        diretor.update("José", date);

        template.postForObject("/api/diretor", diretor, Diretor.class);
        response = template.getForEntity("/api/diretor", String.class);
    }

    @Quando("^O cliente chama a api de diretor$")
    public void o_cliente_chama_a_api_de_diretor() {
        responseObject = template.getForObject("/api/diretor", Diretor[].class);
    }

    @Entao("^Valido o codigo de retorno (\\d+)$")
    public void validoOCodigoDeRetorno(int statusCode) {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " +
                response.getBody(), currentStatusCode.value(), is(statusCode));
    }
}
