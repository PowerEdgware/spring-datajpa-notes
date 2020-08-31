package com.study.design;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.study.design.BaseSmsRequest.Method;

import lombok.SneakyThrows;

/**
 * 测试类
 *
 */
@Configuration
@ComponentScan(basePackages = "com.study.design")
public class SmsMain {
	
	//搜集所有合作方实例
	@Autowired
	private Map<String, BaseSmsHandler> smsHandlerMap;

	@SneakyThrows
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SmsMain.class);	
		
		//刷新
		context.refresh();
		
		//获取实例
		SmsMain smsMain=context.getBean(SmsMain.class);
		
		//可实现动态选择
		String whichPartener=PartenerInstanceName.PartenerOne.getInstanceName();
		
		//根据参数whichPartener 获取合作方处理器
		BaseSmsHandler handler=smsMain.smsHandlerMap.get(whichPartener);
				//context.getBean(whichPartener,BaseSmsHandler.class);
		
		//TODO 构建入参
		PartenerOneSmsRequest smsRequest=new PartenerOneSmsRequest();
		smsRequest.setSid("001");
		smsRequest.setMethodType(Method.POST);
		
		//方法调用
		SmsResponse<?> response=handler.handleSms(smsRequest);
		System.out.println(response);
		//输出结果
		//SmsResponse(errcode=9001, errmsg=Interfce err, respData=null)
		
		System.in.read();
		
		context.close();
	}
}
//sms处理器类名称枚举
enum PartenerInstanceName{
	PartenerOne("partenerOneSmsHandler"),
	PartenerTwo("partenerTwoSmsHandler");
	
	private String instanceName;
	
	private PartenerInstanceName(String name) {
		this.instanceName=name;
	}
	
	public String getInstanceName() {
		return this.instanceName;
	}
}
