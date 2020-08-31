package com.study.design;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 *合作方1处理器
 *
 */
@Component
public class PartenerOneSmsHandler extends BaseSmsHandler{
	
	//TODO 覆盖父类 重写URL
	@Value("${partener.one.url}")
	private String selfUrl="http://example.com/send";
	
	@Override
	protected String partenerUrl() {
		return selfUrl;
	}

	@Override
	protected String transformRequest(BaseSmsRequest req) {
		PartenerOneSmsRequest smsRequest=(PartenerOneSmsRequest) req;
		//TODO 获取参数，拼装成第三方接口的参数方式
		
		return "OK";
	}
	

	@Override
	protected SmsResponse<?> buildResponse(String result) {
		//TODO 处理第三方接口返回数据
		if(StringUtils.isBlank(result)) {
			return SmsResponse.error("9001", "Interfce err");
		}
		
		//TODO 返回结果处理
		
		return SmsResponse.ok("0", result);
	}

}
