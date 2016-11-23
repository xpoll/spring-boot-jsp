package site.blmdz.enums;

public enum ErrorEnums {

	ERROR_00001("MethodArgumentTypeMismatchException", "方法参数类型不匹配异常"),
	ERROR_00002("MissingServletRequestParameterException", "缺少必要参数异常"),
	ERROR_00003("UnauthorizedException", "没有权限"),
	ERROR_00004("UnknownAccountException", "用户名不存在"),
	ERROR_00005("LockedAccountException", "锁定帐户异常"),
	ERROR_00006("DisabledAccountException", "禁用帐户异常"),
	ERROR_00007("IncorrectCredentialsException", "用户名或密码不正确"),
	ERROR_00888("Exception", "系统异常"),

	
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
