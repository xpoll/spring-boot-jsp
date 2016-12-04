package site.blmdz.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
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

import lombok.extern.slf4j.Slf4j;
import site.blmdz.enums.ErrorEnums;
import site.blmdz.exception.AuthenticationJspException;
import site.blmdz.exception.WebJspException;
import site.blmdz.model.Response;
import site.blmdz.model.StringConstants;

/**
 * 最上级全局异常封装处理
 * @author yangyz
 * @date 2016年12月2日下午5:17:35
 */
@Slf4j
@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = WebJspException.class)
	@ResponseBody
    public Response<?> webJspExceptionHandler(
    		WebJspException e) throws Exception {
		
		log.debug("WebJspException");
		
		return Response.faild().buildEnum(e.getErrorEnums());
    }
	
	@ExceptionHandler(value = AuthenticationException.class)
	@ResponseBody
    public Response<?> authenticationJspExceptionHandler(
    		AuthenticationException e) throws Exception {
		
		log.debug("AuthenticationException");
		
        if (e instanceof IncorrectCredentialsException)
        	return Response.faild().buildEnum(ErrorEnums.ERROR_000007);
        else if (e instanceof AuthenticationJspException)
        	return Response.faild().buildEnum(((AuthenticationJspException)e).getErrorEnums());
        else
        	return Response.faild().buildEnum(ErrorEnums.ERROR_999999);
    }

	@ExceptionHandler(value = {Exception.class, RuntimeException.class, Throwable.class})
	@ResponseBody
    public ModelAndView defaultExceptionHandler(
    		HttpServletRequest request,
    		Exception e) throws Exception {
		
		log.debug("Exception");
		
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        
        if (e instanceof MethodArgumentTypeMismatchException)
        	request.setAttribute("info", ErrorEnums.ERROR_000001.desc());
        else if (e instanceof MissingServletRequestParameterException)
        	request.setAttribute("info", ErrorEnums.ERROR_000002.desc());
        else if (e instanceof UnauthorizedException) //TODO 没用
        	request.setAttribute("info", ErrorEnums.ERROR_000003.desc());
        else if (e instanceof UnknownAccountException)
        	request.setAttribute("info", ErrorEnums.ERROR_000004.desc());
        else if (e instanceof LockedAccountException)
        	request.setAttribute("info", ErrorEnums.ERROR_000005.desc());
        else if (e instanceof DisabledAccountException)
        	request.setAttribute("info", ErrorEnums.ERROR_000006.desc());
        else if (e instanceof IncorrectCredentialsException)
        	request.setAttribute("info", ErrorEnums.ERROR_000007.desc());
        else
        	request.setAttribute("info", ErrorEnums.ERROR_999999.desc());
        
        return new ModelAndView(StringConstants.ERRORS);
    }
}
