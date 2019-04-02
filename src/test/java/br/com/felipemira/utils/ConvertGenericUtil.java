package br.com.felipemira.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertGenericUtil {
	
	public static String convertObjectToString(Object objeto) {
		ObjectMapper mapper = new ObjectMapper();
		String body = null;
		try {
			body = mapper.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			System.out.println("Message Error: <br/>" + e.getMessage());
			assert false;
		} 
		return body;
	}

}
