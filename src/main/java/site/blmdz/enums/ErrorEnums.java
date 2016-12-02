package site.blmdz.enums;

public enum ErrorEnums {

	ERROR_000001("000001", "方法参数类型不匹配异常"),
	ERROR_000002("000002", "缺少必要参数异常"),
	ERROR_000003("000003", "没有权限"),
	ERROR_000004("000004", "用户名不存在"),
	ERROR_000005("000005", "用户被锁定"),
	ERROR_000006("000006", "用户被冻结"),
	ERROR_000007("000007", "用户名或密码不正确"),
	ERROR_000008("000008", "用户未激活"),
	ERROR_000009("000009", "该用户已删除"),
	ERROR_000010("000010", "用户状态异常"),

	ERROR_001001("001001", "验证码错误"),
	
	ERROR_000000("000000", "success"),
	ERROR_999998("999998", "数据为空"),
	ERROR_999999("999999", "系统异常"),
	
	
	ERROR_888888("888888", "测试")
	
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
