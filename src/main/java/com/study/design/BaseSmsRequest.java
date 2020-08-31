package com.study.design;

import lombok.Data;

/**
 * 请求封装父类
 *
 */
@Data
public abstract class BaseSmsRequest {

	//调用第三方接口方式
	private Method methodType=Method.GET;
	
	//TODO  其他共有属性
	
	public static enum Method{
		GET,POST;
	}
}
