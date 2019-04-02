package br.com.felipemira.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.Iterator;

public class CommonsMethods {

	public static void addHeadersInTemplate(TestRestTemplate template, MultiValueMap<String, String> headers) {
		MultiValueMap<String, String> headersRoot = new LinkedMultiValueMap<>();
		headersRoot.addAll(headers);
		
		logHeaders(headersRoot);
		
		template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
			request.getHeaders().addAll(headersRoot);
			return execution.execute(request, body);
		}));
	}
	
	public static void logHeaders(MultiValueMap<String, String> headers) {
		Iterator<String> it = headers.keySet().iterator();
		
		String headersString = "Headers: ";
		while (it.hasNext()) {
			String theKey = (String)it.next();
			headersString = headersString + "<br> - Chave: " + theKey + " Valor: " + headers.getFirst(theKey);
		}
		System.out.println(headersString);
	}

	public static void validarChaveValorJson(ResponseEntity<?> responseEntity, String chave, String valor) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse((String) responseEntity.getBody());
			assert(json.get(chave).toString().contains(valor));
			System.out.println("Validacao realizada: <br>-Valor recebido: " + json.get(chave).toString() + "<br>-Valor esperado: " + valor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
