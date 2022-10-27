package com.lanbridge.advancequery.advance.support;

import com.lanbridge.advancequery.advance.request.Filter;

import java.util.Map;

/**
 * @Description 模板
 * @Author wangcheng
 * @Date 2022/10/24 11:21
 */
public abstract class AbstractSqlStatementHandle implements SqlStatementHandle{

    protected String pattern;

    public AbstractSqlStatementHandle(String pattern) {
        this.pattern = pattern;
    }

    /**
     * 1、将值映射关系放入bind  k:p0 v:value
     * 2、返回 #{bind.p0}
     * @param filter
     * @param bind
     * @param namePrefix
     * @return
     */
    abstract String handle(Filter filter, Map<String, Object> bind, String namePrefix);

    @Override
    public String createCondition(Filter filter, Map<String, Object> bind, String namePrefix) {
        String sqlCondition = handle(filter, bind, namePrefix);
        if (pattern.equals("%s")){
            return String.format(pattern,sqlCondition);
        }
        return String.format(pattern,filter.getColum(),sqlCondition);
    }
}
