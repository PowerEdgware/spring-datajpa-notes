package com.study.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.ImmutableMap;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.study.jpa.projections.PersonRepos;
import com.study.jpa.repos.UserRepos;
import com.study.jpa.specifications.CustomerRepos;
import com.study.service.ProjectionService;
import com.study.service.SpecsService;
import com.study.service.UserService;

@Configuration
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT ,basePackages = {"com.study.jpa"})
@EnableJpaAuditing//jpa审计
@EnableTransactionManagement//开启事务
@Import(ScanConfguration.class)
public class JpaConfiguration {//QuerydslPredicateExecutor
//SimpleJpaRepository
	
	@Bean public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager=new JpaTransactionManager(entityManagerFactory);
		return transactionManager;
	}
	//EntityManagerFactory
	@Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean lcem=new LocalContainerEntityManagerFactoryBean();
		
		lcem.setBootstrapExecutor(new SimpleAsyncTaskExecutor());
		lcem.setDataSource(dataSource);
		
		lcem.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		
		HibernateJpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		lcem.setJpaVendorAdapter(jpaVendorAdapter);
		lcem.setJpaDialect(jpaVendorAdapter.getJpaDialect());
		
		//TODO 开启二级缓存
		ImmutableMap<String,String> jpaProperties=ImmutableMap.
				of("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory",
						"hibernate.cache.use_second_level_cache","true",
						"hibernate.cache.use_query_cache","true",
						"javax.persistence.sharedCache.mode","ENABLE_SELECTIVE",
						"hibernate.physical_naming_strategy","com.study.util.CustomePhysicalNamingStrategy");
		lcem.setJpaPropertyMap(jpaProperties);
		
		return lcem;
	}
	
	@Bean DataSource dataSource() {
		return new ComboPooledDataSource();
	}
	
	////////BEAN DEFINITIONS
//	@Bean UserService userService(UserRepos userRepos) {
//		return new UserService(userRepos);
//	}
//	
//	@Bean ProjectionService projectionService(PersonRepos personRepos) {
//		return new ProjectionService(personRepos);
//	}
//	
//	@Bean SpecsService specsService(CustomerRepos customerRepos) {
//		return new SpecsService(customerRepos);
//	}
}
