package com.lanbridge.advancequery.po;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description TODO
 * @Author wangcheng
 * @Date 2022/10/25 17:00
 */
@Component
public class AppRunner implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, SqlSessionFactory> sqlSessionFactory = applicationContext.getBeansOfType(SqlSessionFactory.class);
        System.out.println("===========");
        System.out.println(sqlSessionFactory.size());
    }
}
