####Spring Data JDBC

>简单易用
>[参考文档](https://docs.spring.io/spring-data/jdbc/docs/1.1.7.RELEASE/reference/html/#reference)

1.特点  
相比于spring-data-jpa 不提供缓存，不提供懒加载等特性，简单易用，但存在很多限制。  

2.依赖   

```
<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-jdbc</artifactId>
		</dependency>
```

3.配置   

参见：`com.study.config.JdbcConfiguration`  

例子：
`com.study.jdbc.*`   
JDBCMain.java

#### Spring Data JPA



####Hibernate Cache

