package ptgl.shopping.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import ptgl.shopping.model.Products;

@Service
public class ResfulService implements IResfulService {

	public Object callGetES(String urlES) throws IOException {

		URL url = new URL(urlES);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		StringBuffer response = new StringBuffer();
		String st = "";
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			response.append(output);
			st.concat(output);
		}

		conn.disconnect();
		System.out.println(response);

		DocumentContext jsonContext = JsonPath.parse(response.toString());
		Object result = jsonContext.read("$._source");

		return result;

	}

	public Object convertJson2Object(String json, Class className) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Object p = mapper.readValue(json, className);
		return p;

	}

	public String convertObject2Json(Object obj) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String product = mapper.writeValueAsString(obj);
		return product;

	}

}
