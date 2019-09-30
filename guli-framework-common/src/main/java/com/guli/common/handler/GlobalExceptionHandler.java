package com.guli.common.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.util.ExceptionUtil;
import com.guli.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 * @ControllerAdvice 异常通知处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	//异常处理方法
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R error(Exception e){
		//e.printStackTrace();//输出异常堆栈信息
		log.error(e.getMessage());
		return R.error();
	}

	@ExceptionHandler(BadSqlGrammarException.class)
	@ResponseBody
	public R error(BadSqlGrammarException e){
		//e.printStackTrace();//输出异常堆栈信息
		log.error(e.getMessage());
		return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public R error(JsonParseException e){
		//e.printStackTrace();//输出异常堆栈信息
		log.error(e.getMessage());
		return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
	}

	//自定义异常
	@ExceptionHandler(GuliException.class)
	@ResponseBody
	public R error(GuliException e){
		//e.printStackTrace();//输出异常堆栈信息
		//log.error(e.getMessage());
		//打印异常堆栈信息
		log.error(ExceptionUtil.getMessage(e));
		return R.error().message(e.getMessage()).code(e.getCode());
	}

}