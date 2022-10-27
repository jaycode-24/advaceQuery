package com.lanbridge.advancequery.advance.support;

import com.google.common.base.Splitter;
import com.lanbridge.advancequery.advance.request.Filter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description IN 语句处理器
 * @Author wangcheng
 * @Date 2022/10/24 14:13
 */
public class BelongSqlStatementHandle extends AbstractSqlStatementHandle{

    private final static Splitter SPLITTER = Splitter.on(",");
    public BelongSqlStatementHandle(String pattern) {
        super(pattern);
    }

    @Override
    String handle(Filter filter, Map<String, Object> bind, String namePrefix) {
        if (filter.getValue() instanceof List){
            List<Object> valueList = (List<Object>) filter.getValue();
            String valueStatement = "in (";

            for (int i = 0; i < valueList.size(); i++) {
                //p00,p01...
                String newNamePrefix = namePrefix + i;
                valueStatement = valueStatement + "#{bind." + newNamePrefix + "},";
                bind.put(newNamePrefix,valueList.get(i));
            }
            return valueStatement.substring(0,valueStatement.length()-1) + ")";
        }
        return "";

    }




    public static void main(String[] args) {
        String[] strings = new String[]{"a","c","b"};
        String s = strings.toString();
        System.out.println(s);
        List<String> strings1 = SPLITTER.splitToList(s);

        for (String s1 : strings1) {
            System.out.println(s1);
        }
    }
}
