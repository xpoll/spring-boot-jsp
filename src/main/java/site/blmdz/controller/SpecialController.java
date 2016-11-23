package site.blmdz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SpecialController {

	@RequestMapping(value = {"/hi", "/", "hello"}, method = RequestMethod.GET)
	public String helloView() {
		return "hello";
	}
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String indexView (){
		return "index";
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}
	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public String unauthView() {
		return "unauth";
	}
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successView() {
		return "success";
	}
}
