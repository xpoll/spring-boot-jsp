package site.blmdz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.blmdz.enums.ErrorEnums;
import site.blmdz.exception.WebJspException;

@Controller
@RequestMapping(value="/test")
public class TestController {

	@RequestMapping(value = "error1", method = RequestMethod.GET)
	public String error1() throws Exception {
		throw new Exception("jljljlkjlkjlj");
	}
	
	@ResponseBody
	@RequestMapping(value = "error2", method = RequestMethod.GET)
	public String error2() throws Exception {
		throw new Exception("jljljlkjlkjlj");
	}

	@RequestMapping(value = "error01", method = RequestMethod.GET)
	public String error01() {
		throw new WebJspException(ErrorEnums.ERROR_888888);
	}
	
	@ResponseBody
	@RequestMapping(value = "error02", method = RequestMethod.GET)
	public String error02() throws Exception {
		throw new WebJspException(ErrorEnums.ERROR_888888);
	}

	@RequestMapping(value = "error3", method = RequestMethod.GET)
	public String error3(@RequestParam("code") String code) throws Exception {
		return "success";
	}

	@RequestMapping(value = "error4", method = RequestMethod.GET)
	public String error4(Long code) throws Exception {
		return "success";
	}
	
//	@Autowired
//	DefaultWebSessionManager sessionManager;
//	
//
//	@RequestMapping(value = "session", method = RequestMethod.GET)
//	@ResponseBody
//	public String session() {
//		Collection<Session> collection = sessionManager.getSessionDAO().getActiveSessions();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("现在时间：" + sdf.format(new Date()));
//		collection.forEach(session -> {
//			System.out.println("------------------");
//			System.out.println("session ID ：" + session.getId());
//			System.out.println("session超时时间：" + session.getTimeout());
//			System.out.println("session创建时间：" + sdf.format(session.getStartTimestamp()));
//			System.out.println("session最后请求时间：" + sdf.format(session.getLastAccessTime()));
//			System.out.println("session用户：" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
//		});
//		return "";
//	}
}
