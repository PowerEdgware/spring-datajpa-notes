package com.study.jpa.specifications;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.study.jpa.repos.BaseRepos;

public interface CustomerRepos extends BaseRepos<Customer>,JpaSpecificationExecutor<Customer>{

}
