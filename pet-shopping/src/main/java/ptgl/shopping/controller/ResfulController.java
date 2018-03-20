package ptgl.shopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import ptgl.shopping.common.PetConstants;
import ptgl.shopping.common.PetProperties;
import ptgl.shopping.model.Products;
import ptgl.shopping.service.IResfulService;
import ptgl.shopping.service.ResfulService;

@Controller
public class ResfulController {

	@Autowired
	private IResfulService resfulService;

	private static final Logger LOGGER = Logger.getLogger(ResfulController.class);
	
	// @Autowired
	// private PetConstants petConstants;

	@Autowired
	private PetProperties petProperties;

	@RequestMapping(value = "/es/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getES() throws IOException {

		// use properties file
		String url = petProperties.getEsUrl() + "index/type/01";

		Object result = resfulService.callGetES(url);

		Products pro = (Products) resfulService.convertJson2Object(result.toString(), Products.class);

		return new ResponseEntity<Object>(result, HttpStatus.OK);

	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ResponseEntity<?> init() throws IOException {

		Products pet = new Products();
		pet.setName("dog");
		pet.setType("pet");
		pet.setPrice(123.0);

		String result = resfulService.convertObject2Json(pet);
		Products pro = (Products) resfulService.convertJson2Object(result, Products.class);
		pro.setName("cat");
		List<Products> list = new ArrayList<Products>();
		list.add(pro);
		list.add(pet);

		String url = PetConstants.ElasticSearch + "index/type/" + pet.getId().toString();
		int response = resfulService.callPostES(url, result);

		if (response == 200) {
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

	}
	
	@RequestMapping(value = "/getAll/{type}", method = RequestMethod.GET)
	public List<String> getAll(@PathVariable String type) throws IOException {
		List<String> results = new ArrayList();

		String url = petProperties.getEsUrl() + "product/"+type;

		Object result = resfulService.getAllES(url);
			
		return results;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("product")Products product, 
      BindingResult result, ModelMap model) throws JsonParseException, JsonMappingException, IOException {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("name", product.getName());
        model.addAttribute("type", product.getType());
        model.addAttribute("price", product.getPrice());
        
        
        String url = PetConstants.ElasticSearch + "product/"+product.getType()+"/"+product.getId();
        String body = resfulService.convertObject2Json(product);
		int response = resfulService.callPostES(url, body);
		LOGGER.info(response);
        
        LOGGER.info(product.getName()+" "+product.getType()+" "+product.getPrice());
        
        return "/menu/addProduct";
    }

}
