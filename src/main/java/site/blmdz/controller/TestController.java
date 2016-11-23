package site.blmdz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/test")
public class TestController {

	@RequestMapping(value = "error1", method = RequestMethod.GET)
	public String error1() throws Exception {
		throw new Exception("jljljlkjlkjlj");
	}

	@RequestMapping(value = "error2", method = RequestMethod.GET)
	public String error2(@RequestParam("code") String code) throws Exception {
		return "success";
	}

	@RequestMapping(value = "error3", method = RequestMethod.GET)
	public String error3(Long code) throws Exception {
		return "success";
	}
	
}
