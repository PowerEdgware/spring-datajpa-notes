package com.study.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;
/**
 * 可参考：BeanPropertyRowMapper
 * @author yu
 *
 * @param <T>
 */
public class BaseRowMapper<T> implements RowMapper<T>{

	private Class<T> mappedClass;
	
	 public BaseRowMapper(Class<T> mClass) {
		this.mappedClass=mClass;
	}
	
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T instance=BeanUtils.instantiateClass(mappedClass);
		extractResultAndPopulate(rs,instance);
		return instance;
	}

	private void extractResultAndPopulate(ResultSet rs, T instance) {
		//db类型转换成 instance属性中的java类型
	}

}
