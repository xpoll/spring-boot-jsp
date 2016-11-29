package site.blmdz.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import site.blmdz.auth.AuthUtils;
import site.blmdz.model.Response;


@RestController
@RequestMapping(value = "/spec")
public class RestSpecialController {


	@RequestMapping(value = "/auth" , method = RequestMethod.GET)
	public Callable<Response<?>> edit(){
		return ()-> Response.build(AuthUtils.readAuthsTree("admin"));
	}
	
}
