package org.mybatis.spring.demo;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@MapperScan("org.mybatis.spring.demo.mapper")
public class DemoConfig {

  ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

  @Bean
  public SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource) throws Exception{
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    Resource[] resources = resourceResolver.getResources("mapper/*.xml");
    bean.setMapperLocations(resources);
    return bean;
  }

  @Bean
  public DataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/test");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    return dataSource;

  }

}
