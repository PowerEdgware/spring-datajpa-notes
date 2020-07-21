package com.study.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.RowMapperMap;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.ConfigurableRowMapperMap;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.TransactionManager;

import com.study.jdbc.Person;
import com.study.jdbc.PersonRepos;
import com.study.jdbc.PersonService;
import com.study.jdbc.UserRepos;
import com.study.jdbc.UserService;
import com.study.jdbc.embbed.CustomUser;
import com.study.jdbc.embbed.Phone;
import com.study.jdbc.rowmapper.PersonRowMapper;

import lombok.Data;

/**
 * Spring-data-jdbc:JDBC based repositories
 *  it does NOT offer caching, lazy loading, write behind or many other features of JPA.
 *  This makes Spring Data JDBC a simple, limited, opinionated ORM.
 * @author you
 *
 */
@Configuration
@EnableJdbcRepositories(basePackages = "com.study.jdbc")
@PropertySource(name ="db_properties", value = { "classpath:datasource.properties" })
@EnableJdbcAuditing//jdbc审计  
public class JdbcConfiguration  extends AbstractJdbcConfiguration {//必须继承 AbstractJdbcConfiguration

	/**
	 * Features
		CRUD operations for simple aggregates with customizable NamingStrategy.
		
		Support for @Query annotations.
		
		Support for MyBatis queries.
		
		Events.
		
		JavaConfig based repository configuration by introducing @EnableJdbcRepositories.
	 */
	@Autowired
	ConfigurableEnvironment env;
	
	 @Bean(destroyMethod = "destroy")
	  public DataSource dataSource() {
		 //EmbeddedDatabase
//	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//	    DataSourceInitializer.
//	    return builder.setType(EmbeddedDatabaseType.H2).build();
		 
		 SingleConnectionDataSource dataSource=new SingleConnectionDataSource(env.getProperty("db.jdbc.url"),
				 env.getProperty("db.jdbc.username"), env.getProperty("db.jdbc.password"), true);//use proxy
		return dataSource;
	  }
	 
	 @Bean public NamedParameterJdbcOperations namedParameterJdbcTemplate(DataSource dataSource) {
		 return new NamedParameterJdbcTemplate(dataSource);
	 }
	 
	 //tx
	 @Bean public TransactionManager transactionManager(DataSource dataSource) {
		 return new DataSourceTransactionManager(dataSource);
	 }
	 
	 //注册romMapper
	// @Bean //TODO @Deprecated
	 public RowMapperMap rowMappers() {
		 return new ConfigurableRowMapperMap()
		 	.register(Person.class, new PersonRowMapper());
	 }
	 
//	 @Bean public QueryMappingConfiguration newRowMappers() {
//		 return new DefaultQueryMappingConfiguration()
//				 .registerRowMapper(Person.class, new PersonRowMapper());
//	 }
	 
	 //TODO 数据库relation到实体映射。最佳用法：使用jdbc提供的即可
	 @Bean public QueryMappingConfiguration containerSuppliedRowMappers() {
		 return new DefaultQueryMappingConfiguration()
				 .registerRowMapper(Person.class, new BeanPropertyRowMapper<>(Person.class, false))
				 .registerRowMapper(CustomUser.class, new BeanPropertyRowMapper<>(CustomUser.class,false))
				 .registerRowMapper(Phone.class, new BeanPropertyRowMapper<>(Phone.class,false));
	 }
	 
	 //////////--------------------custom---------------------------------/////////
	 
	 @Bean PersonService personService(PersonRepos personRepos) {
		 return new PersonService(personRepos);
	 }
	 
	 @Bean public UserService userSerivce (UserRepos userRepos) {
		 return new UserService(userRepos);
	 }
	
	public static void main(String[] args) {
		String qtm=NamingStrategy.INSTANCE.getQualifiedTableName(DemoTestTable.class);
		System.out.println(qtm);//demo_test_table
	}
}

@Data
class DemoTestTable{
	String Column1Name;
	String columnName2;
	int id;
}
