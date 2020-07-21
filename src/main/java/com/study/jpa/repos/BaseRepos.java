package com.study.jpa.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.study.jpa.domain.BaseEntity;

@NoRepositoryBean
public interface BaseRepos<T extends BaseEntity> extends JpaRepository<T, Long> {

//	@Query("select t from #{#entityName} t where t.attribute = ?1")
//	List<T> findAllByAttribute(String attribute);
}
