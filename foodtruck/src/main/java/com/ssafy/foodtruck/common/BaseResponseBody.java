package com.ssafy.foodtruck.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("BaseResponseBody")
public class BaseResponseBody {
	@ApiModelProperty(name="응답 메시지", example = "정상")
	String message = null;

	public BaseResponseBody() {}

	public BaseResponseBody(String message){
		this.message = message;
	}

	public static BaseResponseBody of(String message) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		return body;
	}
}
