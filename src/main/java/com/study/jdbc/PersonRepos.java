package com.study.jdbc;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//TODO SimpleJdbcRepository
public interface PersonRepos extends CrudRepository<Person, Long>{

	//	Spring Data JDBC supports only named parameters.
	@Query(value="select * from t_person u where u.firstname = :fisrtname")//需要一个默认的构造函数
	//different form spring data jpa org.springframework.data.jpa.repository.Query
	List<Person> findByFirstname(@Param("fisrtname")String firstName);
	
	
	@Modifying
	@Query("UPDATE DUMMYENTITY SET name = :name WHERE id = :id")
	boolean updateName(@Param("id") Long id, @Param("name") String name);
}
