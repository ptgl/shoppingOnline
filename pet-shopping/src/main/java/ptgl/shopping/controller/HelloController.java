package ptgl.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "main";

	}
	
	@RequestMapping(value = "/pets", method = RequestMethod.GET)
	public ModelAndView getPets(ModelMap model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/menu/pet");
		List<String> pets = new ArrayList();
		pets.add("Dog");
		pets.add("Cat");
		pets.add("Hamster");
		
		mv.addObject("petList", pets);
		
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

}