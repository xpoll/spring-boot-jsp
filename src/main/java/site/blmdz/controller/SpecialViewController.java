package site.blmdz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SpecialViewController {

	@RequestMapping(value = {"/hi", "/", "hello"}, method = RequestMethod.GET)
	public String helloView() {
		return "/hello";
	}
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String indexView (){
		return "/index";
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginView() {
		return "/login";
	}
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successView() {
		return "/success";
	}
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public String treeView() {
		return "/tree";
	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userView() {
		return "/user";
	}
	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public String errorunauthView() {
		return "/error/unauth";
	}
	@RequestMapping(value = "/include/title", method = RequestMethod.GET)
	public String titleView() {
		return "/include/title";
	}
	@RequestMapping(value = "loginout", method = RequestMethod.GET)
	public String loginout() {
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/";
	}
}
