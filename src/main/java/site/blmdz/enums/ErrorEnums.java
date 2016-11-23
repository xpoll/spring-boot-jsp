package site.blmdz.enums;

public enum ErrorEnums {

	ERROR_000001("MethodArgumentTypeMismatchException", "方法参数类型不匹配异常"),
	ERROR_000002("MissingServletRequestParameterException", "缺少必要参数异常"),
	ERROR_000003("UnauthorizedException", "没有权限"),
	ERROR_000004("UnknownAccountException", "用户名不存在"),
	ERROR_000005("LockedAccountException", "锁定帐户异常"),
	ERROR_000006("DisabledAccountException", "禁用帐户异常"),
	ERROR_000007("IncorrectCredentialsException", "用户名或密码不正确"),
	ERROR_000888("Exception", "系统异常"),

	ERROR_001001("001001", "验证码错误"),
	
	;
	private String code;
	private String desc;
	public String code() {
		return code;
	}
	public String desc() {
		return desc;
	}
	ErrorEnums(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
