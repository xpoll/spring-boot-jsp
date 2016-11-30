package site.blmdz.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.blmdz.auth.AuthUtils;
import site.blmdz.model.Response;
import site.blmdz.service.UserService;


@RestController
@RequestMapping(value = "/spec")
public class RestSpecialController {

	@Autowired UserService userService;

	@RequestMapping(value = "/auth" , method = RequestMethod.GET)
	public Callable<Response<?>> auth(){
		return () -> Response.build(AuthUtils.readAuthsTree("admin"));
	}

	@RequestMapping(value = "/user" , method = RequestMethod.GET)
	public Callable<Response<?>> user(@RequestParam("id") Long id) {
		return () -> Response.build(userService.findUser(id));
	}
	
	@RequestMapping(value = "/userall" , method = RequestMethod.GET)
	public Callable<Response<?>> userAll() {
		return () -> Response.build(userService.findAll());
	}
}
