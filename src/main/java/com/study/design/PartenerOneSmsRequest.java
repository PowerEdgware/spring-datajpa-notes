package com.study.design;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合作方1的请求入参，属性根据合作方1接口参数格式而定
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PartenerOneSmsRequest extends BaseSmsRequest{

	//请求参数字段
	private String uid;
	private String sid;
	//TODO 其他
}
