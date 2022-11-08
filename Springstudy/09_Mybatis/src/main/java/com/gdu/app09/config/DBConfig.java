package com.gdu.app09.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*** 08_Springjdbc_AOP랑 비교하면서 보기 ***/
// 히카리쓰면 DriverManagerDataSource 안씀
// mybatis쓰면 JdbcTemplate 안씀
/********************************************/

/*
	@PropertySource
	: 프로퍼티 파일을 참조할 수 있는 애너테이션
*/
@PropertySource(value = {"classpath:mybatis/config/mybatis.properties"})

@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
public class DBConfig {

	// db.properties 파일을 읽어서 변수에 저장하기
	// ${프로퍼티명}
	@Value(value = "${hikari.driver}")
	private String driver;
	
	@Value(value = "${hikari.url}")
	private String url;
	
	@Value(value = "${hikari.username}")
	private String username;
	
	@Value(value = "${hikari.password}")
	private String password;

	@Value(value = "${mapper.locations}")
	private String mapperLocations;
	
	@Value(value = "${config.location}")
	private String configLocation;

	
	// HikariConfig
	@Bean
	public HikariConfig config() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driver);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		return config;
	}
	
	
	// HikariDataSource
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		return new HikariDataSource(config());
	}
	
	
	/***** mybatis 세팅 *****/
	
	// SqlSessionFactory
	@Bean
	public SqlSessionFactory factory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations)); // 매퍼 여러개 -> 복수임
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
		return bean.getObject();
	}

	
	// SqlSessionTemplate
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(factory());
	}
	
	
	// 트랜잭션 처리를 위한 TransactionManager를 Bean으로 등록한다.
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
}
