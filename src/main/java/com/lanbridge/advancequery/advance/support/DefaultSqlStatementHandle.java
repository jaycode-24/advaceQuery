package com.lanbridge.advancequery.advance.support;

import com.lanbridge.advancequery.advance.request.Filter;

import java.util.Map;

/**
 * @Description 默认sql语句处理器
 * @Author wangcheng
 * @Date 2022/10/24 11:23
 */
public class DefaultSqlStatementHandle extends AbstractSqlStatementHandle{

    public DefaultSqlStatementHandle(String pattern) {
        super(pattern);
    }

    @Override
    String handle(Filter filter, Map<String, Object> bind, String namePrefix) {
        bind.put(namePrefix, filter.getPreProcessValue());
        return "#{bind." + namePrefix + "}";
    }
}