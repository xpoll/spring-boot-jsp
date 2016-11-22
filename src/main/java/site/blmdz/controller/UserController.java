package site.blmdz.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/api/user")
public class UserController {

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public String login(HttpServletRequest request,
			Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
			return "redirect:/index";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("errorMsg", "用户名或密码错误！");
			return "login";
		}
	}
	
}
