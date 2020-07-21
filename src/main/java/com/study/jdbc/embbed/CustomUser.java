package com.study.jdbc.embbed;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("t_user")
@Data
public class CustomUser {
	@Id
	private Long id;
	private String userName;
	private LocalDateTime birth;
	@CreatedDate
	private Date createdDate;
	// idColumn:存在于Phone表中外键key名称,keyColumn对应map key,默认是user的表明+_key
	@MappedCollection(idColumn = "user_id", keyColumn = "phone_num") // key Column代表map的key，且会被存于phone表中
	// Map<String,Phone> phones;
	Set<Phone> phones;
	@LastModifiedDate
	private Date lastUpdated;
}
