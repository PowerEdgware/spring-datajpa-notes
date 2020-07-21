package com.study.jdbc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.study.jdbc.embbed.CustomUser;
import com.study.jdbc.embbed.Phone;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	final UserRepos userRepos;
	
	public CustomUser findOne(Long id) {
		return userRepos.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public CustomUser save(String userName,String phoneName,String phoneNum) {
		CustomUser user=new CustomUser();
		user.setBirth(LocalDateTime.now());
		user.setCreatedDate(Date.from(Instant.now()));
		user.setUserName(userName);
		
		Phone phone=new Phone();
		phone.setName(phoneName);
		phone.setPhoneNum(phoneNum);
		
		//保存user时数据库也会保存phone信息
		user.setPhones(Sets.newHashSet(phone));
		//user.setPhones(ImmutableMap.of(phoneNum,phone));
		
		return userRepos.save(user);
	}
	 @Transactional(timeout = 10)
	public CustomUser findByUserName(String userName){
		return userRepos.findByUserName(userName);
	}
}
