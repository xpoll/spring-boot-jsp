package site.blmdz.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import site.blmdz.enums.ErrorEnums;
import site.blmdz.model.Response;

@RestController
@RequestMapping(value="/api/user")
public class UserControllerRest {

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Callable<Response<?>> login(HttpServletRequest request,
			Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("code") String code){
		if (Strings.isNullOrEmpty(code.toLowerCase())
				|| !code.equals(request.getSession().getAttribute("captchaToken")))
			return ()-> Response.build(null).buildEnum(ErrorEnums.ERROR_001001);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
		}catch(UnknownAccountException e){
			return ()-> Response.build(null).buildEnum(ErrorEnums.ERROR_000004);
		}catch(LockedAccountException e){
			return ()-> Response.build(null).buildEnum(ErrorEnums.ERROR_000005);
		}catch(DisabledAccountException e){
			return ()-> Response.build(null).buildEnum(ErrorEnums.ERROR_000006);
		}catch(IncorrectCredentialsException e){
			return ()-> Response.build(null).buildEnum(ErrorEnums.ERROR_000007);
		}catch(Exception e){
			e.printStackTrace();
			return ()-> Response.faild();
		}
		return ()-> Response.ok();
	}
}
