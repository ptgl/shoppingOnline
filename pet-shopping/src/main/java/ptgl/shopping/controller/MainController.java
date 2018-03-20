package ptgl.shopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ptgl.shopping.common.PetProperties;
import ptgl.shopping.model.Products;
import ptgl.shopping.service.IResfulService;

@Controller
public class MainController {

	@Autowired
	private IResfulService resfulService;

	@Autowired
	private PetProperties petProperties;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "main";

	}
	
	@RequestMapping(value = "/pets", method = RequestMethod.GET)
	public ModelAndView getPets(ModelMap model) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/menu/pet");
		List<String> pets = new ArrayList();
		String url = petProperties.getEsUrl() + "product/pet";
		pets = (List<String>) resfulService.getAllES(url);
		List<Products> pro = (List<Products>) resfulService.convertJson2Object(pets.toString(),  (Class<List<Products>>) new ArrayList<Products>().getClass());
//		pets.add("Dog");
//		pets.add("Cat");
//		pets.add("Hamster");
		
		mv.addObject("petList", pro);
		
		return mv;

	}
	
	@RequestMapping(value = "/mangas", method = RequestMethod.GET)
	public String getManga(ModelMap model) {

		return "manga";

	}
	
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String getBooks(ModelMap model) {

		return "book";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("/menu/addProduct", "product", new Products());
    }

}