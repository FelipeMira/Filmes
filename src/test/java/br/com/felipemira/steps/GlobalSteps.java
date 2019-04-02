package br.com.felipemira.steps;

import br.com.felipemira.cucumber.CucumberRoot;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import org.springframework.http.HttpStatus;

import static br.com.felipemira.ui.automator.utils.core.Selenium.resetDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GlobalSteps extends CucumberRoot {

    @Entao("^Valido o codigo de retorno (\\d+)$")
    public void validoOCodigoDeRetorno(int statusCode) {
        HttpStatus currentStatusCode = getResponse().getStatusCode();
        assertThat("status code is incorrect : " + getResponse().getBody(), currentStatusCode.value(), is(statusCode));
    }


    @E("^reseto o webDriver$")
    public void resetoOWebDriver() {
        resetDriver();
    }
}
