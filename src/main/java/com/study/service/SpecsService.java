package com.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.study.jpa.specifications.Customer;
import com.study.jpa.specifications.CustomerRepos;
import com.study.jpa.specifications.CustomerSpecs;

import lombok.Value;

@Service
@Value
//条件查询
public class SpecsService {

	CustomerRepos customerRepos;
	
	public void batchSave(int cnt) {
		ArrayList<Customer> list=Lists.newArrayList();
		while(cnt-->0) {
			Customer customer=new Customer();
			customer.setCustName("cust_"+cnt);
			list.add(customer);
		}
		customerRepos.saveAll(list);
	}
	
	public List<Customer> specQuery(int years) {
		Specification<Customer> spec=CustomerSpecs.isLongTermCustomer(years);
		Pageable pageable=PageRequest.of(0, 100);
		 return customerRepos.findAll(spec, pageable).getContent();
	}
	
}
