package com.lanbridge.advancequery.advance.interceptor;

import com.lanbridge.advancequery.advance.support.JpaSqlExecuter;
import com.lanbridge.advancequery.po.SysUser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description mybatis插件注入
 * @Author wangcheng
 * @Date 2022/10/25 16:39
 */
@Configuration
public class MybatisPluginAutoConfig {


    @Bean
    //@ConditionalOnBean(SqlSessionFactory.class)
    public InjectConfig injectConfig(){
        return new InjectConfig();
    }

    @Bean
    public JpaSqlExecuter jpaSqlExecuter(){
        return new JpaSqlExecuter();
    }
}
