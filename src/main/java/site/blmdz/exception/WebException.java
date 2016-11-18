package site.blmdz.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WebException.java
 * 异常统一 管理 -- 暂未用
 * @author lm
 * @email <a href="mailto:blmdz521@126.com">lm[blmdz521@126.com]</a>
 * @date 2016年11月18日 下午10:57:37
 */
public interface WebException {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	default public String handleSystemException(
			HttpServletRequest request,
			Exception ex) {
		System.out.println(ex.getMessage());
		return "error_error";
	}
}
//@Controller
//public class TestController implements WebException {
//	
//}