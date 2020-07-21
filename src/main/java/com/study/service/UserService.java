package com.study.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceProperty;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.study.jpa.domain.PlatformUser;
import com.study.jpa.repos.UserRepos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Service
public class UserService {

	final UserRepos userRepos;
	
	@PersistenceContext(
		    unitName = "CRM",
		    properties = {
		        @PersistenceProperty(
		            name="org.hibernate.flushMode",
		            value= "COMMIT"
		        )
		    }
		)
	EntityManager entityManager;
	
	public PlatformUser testSave(String userName) {
		PlatformUser user=new PlatformUser();
		user.setUserName(userName);
		
		return userRepos.save(user);
	}
	
	public PlatformUser typedQuery(String userName) {
		TypedQuery<PlatformUser> typedQuery = entityManager.createQuery(
			    "select p " +
			    "from PlatformUser p " +
			    "where p.userName like :name", PlatformUser.class
			)
		.setParameter("name", userName);
		return typedQuery.getSingleResult();
	}
}
