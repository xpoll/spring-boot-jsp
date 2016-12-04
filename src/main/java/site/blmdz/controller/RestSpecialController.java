package site.blmdz.controller;

import java.util.concurrent.Callable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.blmdz.auth.AuthUtils;
import site.blmdz.model.Response;
import site.blmdz.model.User;
import site.blmdz.service.UserService;


@RestController
@RequestMapping(value = "/spec")
public class RestSpecialController {

	@Autowired UserService userService;

	@RequestMapping(value = "/auth" , method = RequestMethod.GET)
	public Callable<Response<?>> auth(){
		
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.findUserByUserName(username);
		
		return () -> Response.build(AuthUtils.readAuthsRolesTreeMap(user.getRoles()));
	}

	@RequestMapping(value = "/user" , method = RequestMethod.GET)
	public Callable<Response<?>> user(@RequestParam("id") Long id) {
		return () -> Response.build(userService.findUserById(id));
	}

	@RequestMapping(value = "/userall" , method = RequestMethod.GET)
	public Callable<Response<?>> userAll() {
		return () -> Response.build(userService.findAll());
	}
}
