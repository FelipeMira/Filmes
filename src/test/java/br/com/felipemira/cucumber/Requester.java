package br.com.felipemira.cucumber;

import br.com.felipemira.actions.CommonsMethods;
import br.com.felipemira.enums.AppHost;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

@Component
public class Requester extends CucumberRoot {

     @Getter
     @Setter
     MultiValueMap<String, String> headersRequester = new LinkedMultiValueMap<>();

	public void addValueInHeader(String chave, String valor){
		headersRequester.add(chave, valor);
	}

     private String returnURLEnvironment(AppHost appHost, String partialURL) {
          String urlEnvironment = null;

          switch (appHost) {
          case Local:
			  urlEnvironment = getUrlLocal().concat(super.getPort() + super.getParamsToUrl(partialURL));
			  break;
          default:
               System.out.println("Host invalido!");
               assert false;
               break;
          }

          System.out.println("Url: " + urlEnvironment);

          return urlEnvironment;
     }
     
	public void clearParamsTest() {
		super.clearParams();
		super.setHeaders(new LinkedMultiValueMap<>());
		headersRequester.clear();
	}
	
	private void prepareHeaders(boolean utf8, HttpHeaders headerJson) {
	     if (utf8) {
	          headerJson.setContentType(MediaType.APPLICATION_JSON_UTF8);
	          super.headers.add("Content-Type", "application/json;charset=UTF-8");
	     } else {
	          headerJson.setContentType(MediaType.APPLICATION_JSON);
	          super.headers.add("Content-Type", "application/json");
	     }
	}

	public void postApi(AppHost appHost, String url, JSONObject object, boolean utf8, String msgLog) {
		try {
			HttpHeaders headerJson = new HttpHeaders();
			headerJson.addAll(getHeadersRequester());

			url = returnURLEnvironment(appHost, url);
			
			CommonsMethods.addHeadersInTemplate(template, new LinkedMultiValueMap<>());

			prepareHeaders(utf8, headerJson);

			HttpEntity<String> entity = new HttpEntity<>(object.toString(), headerJson);

			System.out.println("Request: <br>-Header" + entity.getHeaders().toString() + "<br>-Body: "
			          + entity.getBody());

			setResponse(template.exchange(new URI(url), HttpMethod.POST, entity, String.class));

			clearParamsTest();
			System.out.println(msgLog);
		} catch (Exception ex) {
			clearParamsTest();
			ex.printStackTrace();
			System.out.println(msgLog + ":<br/>" + ex.getMessage());
			assert false;
		}
	}

	public void putApi(AppHost appHost, String url, JSONObject object, boolean utf8, String msgLog) {
		try {
			HttpHeaders headerJson = new HttpHeaders();
			headerJson.addAll(getHeadersRequester());

			url = returnURLEnvironment(appHost, url);
			
			CommonsMethods.addHeadersInTemplate(template, new LinkedMultiValueMap<>());
			
			prepareHeaders(utf8, headerJson);
			
			HttpEntity<String> entity = new HttpEntity<>(object.toString(), headerJson);
			
			System.out.println("Request: <br>-Header" + entity.getHeaders().toString() + "<br>-Body: "
			          + entity.getBody());
			
			setResponse(template.exchange(new URI(url), HttpMethod.PUT, entity, String.class));

			clearParamsTest();

			System.out.println(msgLog);
		} catch (Exception ex) {
			clearParamsTest();
			ex.printStackTrace();
			System.out.println(msgLog + ":<br/>" + ex.getMessage());
			assert false;
		}
	}

	public void patchApi(AppHost appHost, String url, JSONObject object, boolean utf8, String msgLog) {
		try {
			HttpHeaders headerJson = new HttpHeaders();
			headerJson.addAll(getHeadersRequester());
			
			url = returnURLEnvironment(appHost, url);
			
			CommonsMethods.addHeadersInTemplate(template, new LinkedMultiValueMap<>());
			
			prepareHeaders(utf8, headerJson);
			
			HttpEntity<String> entity = new HttpEntity<>(object.toString(), headerJson);
			
			System.out.println("Request: <br>-Header" + entity.getHeaders().toString() + "<br>-Body: "
			          + entity.getBody());
			
			setResponse(template.exchange(new URI(url), HttpMethod.PATCH, entity, String.class));
			clearParamsTest();
			System.out.println(msgLog);
		} catch (Exception ex) {
		     clearParamsTest();
			ex.printStackTrace();
			System.out.println(msgLog + ":<br/>" + ex.getMessage());
			assert false;
		}
	}

	public void getApi(AppHost appHost, String url, boolean utf8, String msgLog) {
		try {
			HttpHeaders headerJson = new HttpHeaders();
			headerJson.addAll(getHeadersRequester());

			url = returnURLEnvironment(appHost, url);
			
			JSONObject jsonObject = new JSONObject();
			
			CommonsMethods.addHeadersInTemplate(template, new LinkedMultiValueMap<>());
			prepareHeaders(utf8, headerJson);
			
			HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headerJson);
			
			System.out.println("Request: <br>-Header" + entity.getHeaders().toString() + "<br>-Body: " + entity.getBody());
			
			setResponse(template.exchange(new URI(url), HttpMethod.GET, entity, String.class));

			clearParamsTest();
			System.out.println(msgLog);
		} catch (Exception ex) {
			clearParamsTest();
			ex.printStackTrace();
			System.out.println(msgLog + ":<br/>" + ex.getMessage());
			assert false;
		}
	}

	public void deleteApi(AppHost appHost, String url, boolean utf8, String msgLog) {
		try {
			HttpHeaders headerJson = new HttpHeaders();
			headerJson.addAll(getHeadersRequester());

			url = returnURLEnvironment(appHost, url);
			
			JSONObject jsonObject = new JSONObject();
			
			CommonsMethods.addHeadersInTemplate(template, new LinkedMultiValueMap<>());
			
			prepareHeaders(utf8, headerJson);
			
			HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headerJson);
			
			System.out.println("Request: <br>-Header" + entity.getHeaders().toString() + "<br>-Body: "
			          + entity.getBody());
			
			setResponse(template.exchange(new URI(url), HttpMethod.DELETE, entity, String.class));

			clearParamsTest();
			System.out.println(msgLog);
		} catch (Exception ex) {
		     clearParamsTest();
		     ex.printStackTrace();
		     System.out.println(msgLog + ":<br/>" + ex.getMessage());
		     assert false;
		}
	}

}
