package ptgl.shopping.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import ptgl.shopping.controller.ResfulController;
import ptgl.shopping.model.Products;

@Service
public class ResfulService implements IResfulService {

	private static final Logger LOGGER = Logger.getLogger(ResfulService.class);
	
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
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			response.append(output);
		}

		conn.disconnect();
		System.out.println(response);

		DocumentContext jsonContext = JsonPath.parse(response.toString());
		Object result = jsonContext.read("$._source");

		return result;

	}

	public List<String> getAllES(String urlES) throws IOException {

		URL url = new URL(urlES+"/_search");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		StringBuffer response = new StringBuffer();
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			response.append(output);
		}

		conn.disconnect();
		System.out.println(response);

		DocumentContext jsonContext = JsonPath.parse(response.toString());
		Object result = jsonContext.read("$.hits.hits");
		int total = jsonContext.read("$.hits.total");
		JSONArray ESHitSrchObject = new JSONArray(result.toString()); 
		
		List<String> resultList = new ArrayList();
		
		for(int i = 0; i < total; i++){
			JSONObject obj = ESHitSrchObject.getJSONObject(i).getJSONObject("_source");
			resultList.add(obj.toString());
		}
		LOGGER.info(resultList);
		return resultList;

	}
	
	public Object convertJson2Object(String json, Class className)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Object p = mapper.readValue(json, className);
		return p;

	}

	public String convertObject2Json(Object obj) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String product = mapper.writeValueAsString(obj);
		return product;

	}

	
	public int callPostES(String url, Object json) throws IOException {
		String requestMethod = "POST";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(requestMethod);
		con.setRequestProperty("Content-Type", "application/json");
		// Send post request
		con.setDoOutput(true);
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
		wr.write(json.toString());
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();

		BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		con.disconnect();
		
		return responseCode;
	}

}
