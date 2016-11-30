package site.blmdz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.blmdz.auth.AuthUtils;

@Controller
@RequestMapping(value = "/auth")
public class AuthsController {

	@RequestMapping(value = "/tree" , method = RequestMethod.GET)
	public String auth(Model model){

		Subject subject=SecurityUtils.getSubject();
		System.out.println(subject.getPrincipal());
		model.addAttribute("authList", AuthUtils.readAuthsTree("admin"));
		return "/include/tree";
	}
}
