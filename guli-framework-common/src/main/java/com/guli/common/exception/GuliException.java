package com.guli.common.exception;

import com.guli.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 运行时异常不需要处理 所以不能直接extends Exception 需要throw或者try catch
 * extends RuntimeException  对代码没有嵌入性
 *
 *
 */
@Data
@ApiModel(value = "全局异常")
public class GuliException extends RuntimeException {

	@ApiModelProperty(value = "状态码")
	private Integer code;

	/**
	 * 接受状态码和消息
	 * @param code
	 * @param message
	 */
	public GuliException(Integer code, String message) {
		super(message);
		this.code=code;
	}

	/**
	 * 接收枚举类型
	 * @param resultCodeEnum
	 */
	public GuliException(ResultCodeEnum resultCodeEnum) {
		super(resultCodeEnum.getMessage());
		this.code = resultCodeEnum.getCode();
	}

	@Override
	public String toString() {
		return "GuliException{" +
				"code=" + code +
				",message=" + this.getMessage()+
				'}';
	}
}