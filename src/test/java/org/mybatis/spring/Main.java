package org.mybatis.spring;

import org.mybatis.spring.demo.DemoConfig;
import org.mybatis.spring.demo.mapper.DemoMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
    DemoMapper bean = context.getBean(DemoMapper.class);
    System.out.println(bean.getById());
    System.out.println(bean.toString());



  }
}
