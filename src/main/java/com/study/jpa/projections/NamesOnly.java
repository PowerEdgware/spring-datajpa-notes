package com.study.jpa.projections;

import lombok.Value;

/**
 * 只查询person中部分数据
 * @author all
 *
 */
@Value
public class NamesOnly {

	String firstname, lastname;
}
