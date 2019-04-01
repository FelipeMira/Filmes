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

    public void before() {
        //Passo sempre o token para acesso.
        if(headers.isEmpty()) {
            addHeader();
        }
        template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders()
                    .addAll(headers);
            return execution.execute(request, body);
        }));
    }

    public String getPort() {
        return environment.getProperty("local.server.port");
    }

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

    public void clearParams() {
        params.clear();
    }

    public MultiValueMap<String, String> getHeaders() {
        return this.headers;
    }

    private void addHeader() {
        this.headers.add("Accept", "application/json");
    }

    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers.clear();
        addHeader();
        this.headers.addAll(headers);
        before();
        System.out.println("Adicionando parametros no header: " + "</br> " + "- Headers: " + getHeaders().toString());
    }

    public static ResponseEntity<?> getResponse() {
        return response;
    }

    public static void setResponse(ResponseEntity<?> response) {
        System.out.println("Resposta: " + response.toString());
        CucumberRoot.response = response;
    }

    public void getStatusCode(Integer statusCode) {
        HttpStatus currentStatusCode = getResponse().getStatusCode();
        assert(currentStatusCode.value() == statusCode);
        System.out.println("Retorno do Status Code <br>-Esperado: " + statusCode + "<br>-Retornado: " + currentStatusCode.value());
    }

    public void getStatusCode(int statusCode, int statusCodeTwo) {
        HttpStatus currentStatusCode = getResponse().getStatusCode();
        assert(currentStatusCode.value() == statusCode || currentStatusCode.value() == statusCodeTwo);
        System.out.println("Retorno do Status Code <br>-Esperado: " + (currentStatusCode.value() == statusCode? statusCode : statusCodeTwo) + "<br>-Retornado: " + currentStatusCode.value());
    }

    @SuppressWarnings("static-access")
    public void callGetErrorString(String finalUrl) {
        String url = urlLocal.concat(getPort() + getParamsToUrl(finalUrl));
        System.out.println("Url: " + url);
        try {
            this.setResponse(this.template.getForEntity(url, String.class));
            System.out.println("Consulta realizada");
            System.out.println("Body retornado: " + getResponse().getBody());
        }catch(Exception ex) {
            System.out.println("Consulta não realizada: <br><br>" + ex.getMessage());
        }
    }

    public static void logChamadaRealizada(Boolean condition) {
        if(condition) {
            System.out.println("Consulta realizada");
            System.out.println("Body retornado: " + getResponse().getBody());
        }else {
            System.out.println("Consulta não realizada: <br><br>");
            System.out.println("-Body retornado: " + getResponse().getBody());
        }
    }

    public void esperarUmPouco() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

