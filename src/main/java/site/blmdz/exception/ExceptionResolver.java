package site.blmdz.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import site.blmdz.model.ViewsConstant;

/**
 * ExceptionResolver.java
 * 异常解析器
 * @author lm
 * @email <a href="mailto:blmdz521@126.com">lm[blmdz521@126.com]</a>
 * @date 2016年11月18日 下午11:29:11
 */
public class ExceptionResolver extends ExceptionHandlerExceptionResolver {
	
	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
			HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
		
		return new ModelAndView(ViewsConstant.JSP_001);
	}
}
