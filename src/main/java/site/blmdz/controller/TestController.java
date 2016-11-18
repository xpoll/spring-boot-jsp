package site.blmdz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import site.blmdz.model.ViewsConstant;

@Controller
public class TestController {

	@Value("${application.hello}")
	private String value;
	
	@RequestMapping(value = {"/hi", "/"})
	public String helloTest(Map<String, Object> context) {
		context.put("hello", value);
		String[] a = {""};
		System.out.println(a[3]);
		return ViewsConstant.JSP_001;
	}
}
