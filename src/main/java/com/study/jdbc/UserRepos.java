package com.study.jdbc;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.study.jdbc.embbed.CustomUser;

public interface UserRepos extends CrudRepository<CustomUser, Long>{

	@Query("select u.* from t_user u where u.user_name=:userName")//只能是原生sql
	CustomUser findByUserName(@Param("userName")String userName);
}
