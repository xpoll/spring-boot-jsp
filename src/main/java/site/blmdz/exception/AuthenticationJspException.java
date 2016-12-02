package site.blmdz.exception;

import org.apache.shiro.authc.AuthenticationException;

import lombok.Getter;
import site.blmdz.enums.ErrorEnums;

/**
 * extends AuthenticationException
 * 用于shiro登陆验证异常返回
 * @author yangyz
 * @date 2016年12月2日下午5:07:03
 */
public class AuthenticationJspException extends AuthenticationException {
	
	@Getter
	private ErrorEnums errorEnums;

	private static final long serialVersionUID = 1L;

    public AuthenticationJspException() {
        super();
    }

    public AuthenticationJspException(ErrorEnums errorEnums) {
        super(errorEnums.desc());
        this.errorEnums = errorEnums;
    }

    public AuthenticationJspException(String message) {
        super(message);
    }

    public AuthenticationJspException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AuthenticationJspException(Throwable throwable) {
        super(throwable);
    }

}
