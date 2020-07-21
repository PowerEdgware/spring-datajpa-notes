package com.study.jpa.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Table(name="t_order",indexes = {
		// name = "idx_author_first_last_name",
//        columnList = "first_name, last_name",
//        unique = false
})
@Entity
@Cacheable
@Data
public class Order extends BaseEntity{

	@Column(unique = true)
	@ColumnDefault("'N/A'")
	String orderId;
	@ManyToOne
	@JoinColumn(name = "userId",foreignKey = @ForeignKey(name="user_id_FK"))
	PlatformUser user;
	
	@ColumnDefault("-1")
	double price;
	
	
}
