package com.study.jdbc.embbed;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("t_phone")
@Data
public class Phone {
	@Id
	Long id;
	String name;
	String phoneNum;
	@CreatedDate
	private Date dateCreated;
}
