package com.study.jpa.repos;

import org.springframework.data.jpa.repository.Query;

import com.study.jpa.domain.PlatformUser;

public interface UserRepos extends BaseRepos<PlatformUser> {

	@Query("select u from #{#entityName} u where u.userName = ?1")
	PlatformUser findByUserName(String userName);

}
