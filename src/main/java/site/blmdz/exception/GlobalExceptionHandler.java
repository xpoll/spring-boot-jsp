package site.blmdz.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import site.blmdz.enums.ErrorEnums;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
    public ModelAndView defaultErrorHandler(HttpServletRequest request,
    		Exception e) throws Exception {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        if (e instanceof MethodArgumentTypeMismatchException)
        	request.setAttribute("info", ErrorEnums.ERROR_00001.desc());
        else if (e instanceof MissingServletRequestParameterException)
        	request.setAttribute("info", ErrorEnums.ERROR_00002.desc());
        else if (e instanceof UnauthorizedException) //TODO 没用
        	request.setAttribute("info", ErrorEnums.ERROR_00003.desc());
        else if (e instanceof UnknownAccountException)
        	request.setAttribute("info", ErrorEnums.ERROR_00004.desc());
        else if (e instanceof LockedAccountException)
        	request.setAttribute("info", ErrorEnums.ERROR_00005.desc());
        else if (e instanceof DisabledAccountException)
        	request.setAttribute("info", ErrorEnums.ERROR_00006.desc());
        else if (e instanceof IncorrectCredentialsException) {
        	request.setAttribute("info", ErrorEnums.ERROR_00007.desc());
        }
        else
        	request.setAttribute("info", ErrorEnums.ERROR_00888.desc());
        return new ModelAndView("errors");
    }
}
