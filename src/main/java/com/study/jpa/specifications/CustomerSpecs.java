package com.study.jpa.specifications;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecs {
	public static Specification<Customer> isLongTermCustomer(final int years) {
	    return new Specification<Customer>() {
	      public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query,
	            CriteriaBuilder builder) {

	         LocalDate localDate = LocalDate.now().minusYears(years);
	         Instant instant=localDate.atTime(LocalTime.now()).atZone(ZoneId.systemDefault())
	        		 .toInstant();
	         Date date=Date.from(instant);
	         return builder.lessThan(root.get("createdAt"), date);
	      }
	    };
	  }

	/*
	 * public static Specification<Customer> hasSalesOfMoreThan(MonetaryAmount
	 * value) { return new Specification<Customer>() { public Predicate
	 * toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
	 * 
	 * // build query here } }; }
	 */
}
