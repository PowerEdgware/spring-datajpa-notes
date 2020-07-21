package com.study.jpa.examples;

import java.util.Collection;


import com.study.jpa.repos.BaseRepos;

public interface PersonExampleRepos extends BaseRepos<PersonExample>{

	 //Dynamic Projections
	 <T> Collection<T> findByLastname(String lastname, Class<T> type);
}
