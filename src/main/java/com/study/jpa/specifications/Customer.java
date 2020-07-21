package com.study.jpa.specifications;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.study.jpa.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name="t_spec_customer")
@Data
public class Customer extends BaseEntity{

	String custName;
}
