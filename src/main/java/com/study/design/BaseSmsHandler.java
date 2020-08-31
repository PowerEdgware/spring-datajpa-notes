package com.study.design;

import com.study.design.BaseSmsRequest.Method;

/**
 * 基础的消息处理类：后续新增第三方直接继承该类，覆盖方法即可
 *
 */
public abstract class BaseSmsHandler {

	protected String url;// 第三方url

	public final SmsResponse<?> handleSms(BaseSmsRequest smsRequest) {
		// 模板方法，子类覆盖
		String partenerURL = partenerUrl();

		// 转换成第三方接口请求参数
		String reqParams = transformRequest(smsRequest);

		// 请求处理
		String result = doProcess(partenerURL, smsRequest.getMethodType(), reqParams);

		// 处理返回结果
		return buildResponse(result);
	}

	// 调用第三方接口
	protected String doProcess(String partenerURL, Method methodType, String reqParams) {
		// TODO
		String result = "";
		if (Method.GET.name().contentEquals(methodType.name())) {
			// result=Httpclient.httpGet(partenerURL,reqParams);
		} else if (Method.POST.name().contentEquals(methodType.name())) {
			// result=Httpclient.httpPost(partenerURL,reqParams);
		}
		return result;
	}

	/**
	 * 模板方法：转换请求参数，使得符合第三方接口形式
	 * 
	 * @param req
	 * @return
	 */
	protected abstract String transformRequest(BaseSmsRequest req);

	/**
	 * 处理第三方调用结果
	 * 
	 * @param result
	 * @return
	 */
	protected abstract SmsResponse<?> buildResponse(String result);

	//模板方法子类可覆盖
	protected String partenerUrl() {
		return url;
	}
}
