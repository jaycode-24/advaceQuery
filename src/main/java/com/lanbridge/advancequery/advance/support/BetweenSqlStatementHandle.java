package com.lanbridge.advancequery.advance.support;

import com.google.common.base.Splitter;
import com.lanbridge.advancequery.advance.request.Filter;

import java.nio.file.FileStore;
import java.util.List;
import java.util.Map;

/**
 * @Description 介于sql语句
 * @Author wangcheng
 * @Date 2022/10/24 15:05
 */
public class BetweenSqlStatementHandle extends AbstractSqlStatementHandle{

    private final static Splitter SPLITTER = Splitter.on(",");
    public BetweenSqlStatementHandle(String pattern) {
        super(pattern);
    }

    @Override
    String handle(Filter filter, Map<String, Object> bind, String namePrefix) {
        String values = filter.getValue().toString();
        List<String> valueList = SPLITTER.splitToList(values);

        String startNamePrefix = namePrefix + "0";
        String endNamePrefix = namePrefix + "1";

        bind.put(startNamePrefix,valueList.get(0));
        bind.put(endNamePrefix,valueList.get(1));

        //a.create_time >= #{bind.p00} and a.create_time <= #{bind.p01}
        return filter.getColum() + " >= #{bind." + startNamePrefix + "} and " + filter.getColum() + "<= #{bind." + endNamePrefix + "}";

    }
}
