package br.com.felipemira.cucumber;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.com.felipemira.FilmesApplication;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import lombok.Getter;
import lombok.Setter;

/***
 * @author felipe.mira.ext 01/04/2019
 */
@SpringBootTest(classes = FilmesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles={"test", "qa"}, resolver=ProfilesResolver.class)
@ContextConfiguration
public class CucumberRoot {

    @Autowired
    protected TestRestTemplate template;

    @Autowired
    Environment environment;

    @Getter@Setter
    @Value("${api.url.local}")
    private String urlLocal;

    private static ResponseEntity<?> response;

    private static List<NameValuePair> params = new LinkedList<>();

    protected MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    /**
     * Prepara o RestTemplate para o teste
     */
    public void before() {
        if(headers.isEmpty()) {
            addHeader();
        }
        template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders()
                    .addAll(headers);
            return execution.execute(request, body);
        }));
    }

    /**
     * Verifica a porta utilizada no momento pela automacao
     * @return String
     */
    public String getPort() {
        return environment.getProperty("local.server.port");
    }

    /**
     * Prepara a url para a chamada no TestRestTemplate
     * @param string url
     * @return String
     */
    public String getParamsToUrl(String string) {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        String finalUrl = "";

        if(!paramString.equals("")) {
            finalUrl = "?" + paramString;
        }
        return string + finalUrl;
    }

    /**
     * Adicionar parametro ao caminho do servico de consulta
     * @param chave String parametro
     * @param valor String valor da chave
     */
    public void addValueInParam(String chave, String valor){
        params.add(new BasicNameValuePair(chave, valor));
    }

    /**
     * Limpa os parametros
     */
    public void clearParams() {
        params.clear();
    }

    /**
     * retorna os headers setados para o momento da chamada
     * @return MultiValueMap<String, String>
     */
    public MultiValueMap<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * Adiciona o parametro que aceita o retorno json nas chamadas efetuadas pelo TestRestTemplate
     */
    private void addHeader() {
        this.headers.add("Accept", "application/json");
    }

    /**
     * Seta parametros passados para o Header
     * @param headers MultiValueMap<String, String> headers
     */
    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers.clear();
        addHeader();
        this.headers.addAll(headers);
        before();
        System.out.println("Adicionando parametros no header: " + "</br> " + "- Headers: " + getHeaders().toString());
    }

    /**
     * Retorna o Response da chamada
     * @return ResponseEntity<?>
     */
    public static ResponseEntity<?> getResponse() {
        return response;
    }

    /**
     * Seta o response apos alguma chamada efetuada
     * @param response ResponseEntity<?>
     */
    public static void setResponse(ResponseEntity<?> response) {
        System.out.println("Resposta: " + response.toString());
        CucumberRoot.response = response;
    }

    /**
     * Valida um o status code da chamada efetuada
     * @param statusCode Integer
     */
    public void getStatusCode(Integer statusCode) {
        HttpStatus currentStatusCode = getResponse().getStatusCode();
        assert(currentStatusCode.value() == statusCode);
        System.out.println("Retorno do Status Code <br>-Esperado: " + statusCode + "<br>-Retornado: " + currentStatusCode.value());
    }

    /**
     * Valida dois status code, se o retorno da chamada for igual a algum deles o retorno sera true
     * @param statusCode int
     * @param statusCodeTwo int
     */
    public void getStatusCode(int statusCode, int statusCodeTwo) {
        HttpStatus currentStatusCode = getResponse().getStatusCode();
        assert(currentStatusCode.value() == statusCode || currentStatusCode.value() == statusCodeTwo);
        System.out.println("Retorno do Status Code <br>-Esperado: " + (currentStatusCode.value() == statusCode? statusCode : statusCodeTwo) + "<br>-Retornado: " + currentStatusCode.value());
    }

    /**
     * Seta o log com a chamada realizada
     * @param condition boolean
     */
    public static void logChamadaRealizada(Boolean condition) {
        if(condition) {
            System.out.println("Consulta realizada");
            System.out.println("Body retornado: " + getResponse().getBody());
        }else {
            System.out.println("Consulta não realizada: <br><br>");
            System.out.println("-Body retornado: " + getResponse().getBody());
        }
    }

    /**
     * Espera cinco segundos
     */
    public void esperarUmPouco() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

