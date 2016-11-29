package site.blmdz.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import site.blmdz.enums.ErrorEnums;

@ToString
@Getter
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;
	@Getter(AccessLevel.NONE)
	private Boolean success;
	private T data;
	

	public static<T> Response<T> build() {
		return build(null);
	}
	public static<T> Response<T> build(T data) {
		Response<T> response = new Response<T>();
		if (data == null) {
			response.code = ErrorEnums.ERROR_999998.code();
			response.message = ErrorEnums.ERROR_999998.desc();
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
