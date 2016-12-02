package site.blmdz.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.blmdz.model.Response;
import site.blmdz.model.User;
import site.blmdz.service.UserService;

/**
 * 用户Rest请求
 * @author yangyz
 * @date 2016年12月2日下午5:13:01
 */
@RestController
@RequestMapping(value="/api/user")
public class UserControllerRest {
	
	@Autowired UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Callable<Response<?>> login(HttpServletRequest request,
			Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("code") String code){
		
//		if (Strings.isNullOrEmpty(code)
//				|| !code.toLowerCase().equals(request.getSession().getAttribute(Captcha.TOKEN)))
//			return ()-> Response.build(null).buildEnum(ErrorEnums.ERROR_001001);
		
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		subject.login(token);
		
		return ()-> Response.ok();
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Callable<Response<?>> add(HttpServletRequest request,
			Model model, User user){
		
		userService.create(user);
		
		return ()-> Response.ok();
	}
	
}
