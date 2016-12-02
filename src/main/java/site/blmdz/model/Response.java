package site.blmdz.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import site.blmdz.enums.ErrorEnums;

@Data
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	private String code;
	
	@Setter(AccessLevel.NONE)
	private String message;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Boolean success;
	
	@Setter(AccessLevel.NONE)
	private T data;

	public static Response<Boolean> ok() {
		return build(Boolean.TRUE);
	}
	
	public static<T> Response<T> ok(T data) {
		return build(data);
	}
	
	public static Response<Boolean> faild() {
		return build(null);
	}
	
	public static<T> Response<T> build(T data) {
		Response<T> response = new Response<T>();
		if (data == null) {
			response.code = ErrorEnums.ERROR_999999.code();
			response.message = ErrorEnums.ERROR_999999.desc();
			response.success = Boolean.FALSE.booleanValue();
			return response;
		}
		response.code = ErrorEnums.ERROR_000000.code();
		response.message = ErrorEnums.ERROR_000000.desc();
		response.success = Boolean.TRUE.booleanValue();
		response.data = data;
		return response;
	}

	public Response<T> buildEnum(ErrorEnums enums) {
		this.code = enums.code();
		this.message = enums.desc();
		return this;
	}
	public Response<T> error() {
		this.success = Boolean.FALSE.booleanValue();
		return this;
	}

	public Boolean isSuccess() {
		return success;
	}

}
