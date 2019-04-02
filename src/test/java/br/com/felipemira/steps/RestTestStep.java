package br.com.felipemira.steps;

import com.jayway.restassured.http.ContentType;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestTestStep {

    String url;

    String complemento;

    @Dado("^Que quero testar o retorno do jsonplaceholder$")
    public void queQueroTestarORetornoDoJsonplaceholder() {
        url = "https://jsonplaceholder.typicode.com/todos/";
    }

    @Quando("^O cliente passa o complemento da url \"([^\"]*)\"$")
    public void oClientePassaOComplementoDaUrl(String complemento) throws Throwable {
        this.complemento = complemento;
    }

    @Entao("^Chamo a api rest e valido os retornos$")
    public void chamoAApiRestEValidoOsRetornos() {
        given()
                .relaxedHTTPSValidation()
                .when()
                .get(url + complemento)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", is(1))
                .body("title", equalTo("delectus aut autem"))
                .body("completed", is(false));
    }
}
