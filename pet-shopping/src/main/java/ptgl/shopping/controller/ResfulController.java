package ptgl.shopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import ptgl.shopping.common.PetConstants;
import ptgl.shopping.common.PetProperties;
import ptgl.shopping.service.ResfulService;

@Controller
public class ResfulController {

	@Autowired
	private ResfulService resfulService;
	
	@Autowired
	private PetConstants petConstants;
	
	@Autowired
	private PetProperties petProperties;
	
	
	@RequestMapping(value = "/es/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getES() throws IOException {

		// use constants
		//String url = petConstants.ElasticSearch + "index/type/01";
		
		// use properties file
		String url = "index/type/01";
		
		Object result = resfulService.callGetES(url);
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
}
