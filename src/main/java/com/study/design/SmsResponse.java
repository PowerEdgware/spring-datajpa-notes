package com.study.design;

import lombok.Data;

/**
 * 接口响应数据封装类
 *
 * @param <D>
 */
@Data
public class SmsResponse 
<D>{

	String errcode;
	String errmsg;
	D respData;//接口返回数据
	
	public static SmsResponse<?> error(String errcode,String errmsg){
		SmsResponse<?> response=new SmsResponse<>();
		response.setErrcode(errcode);
		response.setErrmsg(errmsg);
		return response;
	}
	
	public static  <D> SmsResponse<D> ok(String errcode,D data){
		SmsResponse<D> response=new SmsResponse<>();
		response.setErrcode(errcode);
		response.setRespData(data);
		return response;
	}
}
