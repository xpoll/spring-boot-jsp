package site.blmdz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.blmdz.auth.AuthUtils;
import site.blmdz.model.User;
import site.blmdz.service.UserService;

@Controller
@RequestMapping(value = "/auth")
public class AuthsController {
	
	@Autowired UserService userService;

	@RequestMapping(value = "/tree" , method = RequestMethod.GET)
	public String auth(Model model){

		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.findUserByUserName(username);
		
		model.addAttribute("authList", AuthUtils.readAuthsTree(user.getRoles()));
		
		return "/include/tree";
	}
}
