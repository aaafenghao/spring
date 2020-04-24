package org.mybatis.spring;

import org.mybatis.spring.demo.DemoConfig;
import org.mybatis.spring.demo.mapper.DemoMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

  /**
   * 1、在Spring容器启动的过程中,mapper接口装配到Spring容器的beanClass是MapperFactoryBean
   * 2、当我们从Spring容器中获取对象的时候,调用的是MapperFactoryBean类的getObject方法,返回的是MapperProxy代理对象
   *    通过打印对象,可以具体看到
   * @param args
   */
  public static void main(String[] args) {

    //处理MapperScans注解,添加相关的BeanClass为MapperFactoryBean,到Spring容器中
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

//  在应用上下文初始化完成后,装配到Spring IOC 容器的是MapperFactoryBean
//  因为MapperFactoryBean实现了SqlSessionDaoSupport接口,继而实现了InitializingBean接口
//  所以在MapperFactoryBean实例化之后会调用父类方法afterPropertiesSet()
//  DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getBeanFactory();
//  String[] singletonNames = beanFactory.getSingletonNames();

    DemoMapper bean = context.getBean(DemoMapper.class);

    System.out.println(bean.getById());
    System.out.println(bean.toString());
    //org.apache.ibatis.binding.MapperProxy@2118cddf

    //1、Spring上下文的初始化
    //首先注册MapperScannerConfigurer的bd,然后扫描Mapper接口信息,添加对应的bd到Spring中,将BeanClass设置成MapperFactoryBean
    //设置一些需要的属性值
    //在MapperFactoryBean对象初始化的时候,会调用InitializingBean接口的afterPropertiesSet方法,完后接口对应xml的解析,然后将
    //方法封装起来
    //2、从Spring容器中获取Mapper接口对应的Bean
    //获取对象是调用MapperFactoryBean对象的getObject方法,返回一个代理类,对应的InvocationHandler为MapperProxy
    //3、进行方法的调用
    //调用对应的InvocationHandler方法中的invoke方法


  }
}
