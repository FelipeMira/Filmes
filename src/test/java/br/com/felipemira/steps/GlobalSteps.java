package br.com.felipemira.steps;

import br.com.felipemira.cucumber.CucumberRoot;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GlobalSteps extends CucumberRoot {

    @Entao("^Valido o codigo de retorno (\\d+)$")
    public void validoOCodigoDeRetorno(int statusCode) {
        HttpStatus currentStatusCode = getResponse().getStatusCode();
        assertThat("status code is incorrect : " + getResponse().getBody(), currentStatusCode.value(), is(statusCode));
    }


}
