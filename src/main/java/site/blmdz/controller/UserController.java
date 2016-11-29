package site.blmdz.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Strings;

import site.blmdz.enums.ErrorEnums;

@Controller
@RequestMapping(value="/api/user")
public class UserController {

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("code") String code) throws Exception {
		if (Strings.isNullOrEmpty(code.toLowerCase())
				|| !code.equals(request.getSession().getAttribute("captchaToken")))
			throw new Exception(ErrorEnums.ERROR_001001.desc());
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
			return "redirect:/index";
		}catch(UnknownAccountException e){
			request.setAttribute("msg", ErrorEnums.ERROR_000004.desc());
		}catch(LockedAccountException e){
			request.setAttribute("msg", ErrorEnums.ERROR_000005.desc());
		}catch(DisabledAccountException e){
			request.setAttribute("msg", ErrorEnums.ERROR_000006.desc());
		}catch(IncorrectCredentialsException e){
			request.setAttribute("msg", ErrorEnums.ERROR_000007.desc());
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", ErrorEnums.ERROR_000888.desc());
		}
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		return "login";
	}
	@RequestMapping(value = "loginout", method = RequestMethod.GET)
	public String loginout() {
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
}
