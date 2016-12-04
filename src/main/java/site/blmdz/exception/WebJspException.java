package site.blmdz.exception;

import lombok.Getter;
import site.blmdz.enums.ErrorEnums;

/**
 * extends RuntimeException
 * 项目异常返回
 * @author yangyz
 * @date 2016年12月2日下午5:07:58
 */
public class WebJspException extends RuntimeException {
	
	@Getter
	private ErrorEnums errorEnums;

	private static final long serialVersionUID = 1L;

    public WebJspException() {
        super();
    }

    public WebJspException(ErrorEnums errorEnums) {
        super(errorEnums.desc());
        this.errorEnums = errorEnums;
    }

    public WebJspException(String message) {
        super(message);
    }

    public WebJspException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WebJspException(Throwable throwable) {
        super(throwable);
    }

}
