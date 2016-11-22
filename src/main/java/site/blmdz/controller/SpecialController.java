package site.blmdz.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SpecialController {

	@RequestMapping(value="index", method=RequestMethod.GET)
	public String indexView (){
		return "index";
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}
	@RequestMapping(value = {"/hi", "/", "hello"})
	public String helloView(Map<String, Object> context) {
		return "hello";
	}
}
