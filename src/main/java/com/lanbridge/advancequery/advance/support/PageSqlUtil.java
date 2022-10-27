package com.lanbridge.advancequery.advance.support;

/**
 * @Description 返回分页语句
 * @Author wangcheng
 * @Date 2022/10/26 10:19
 */
public class PageSqlUtil {


    public static String mysqlCountSql(String sql){
        return "select count(*) from (" + sql + ") example";
    }

    public static String mysqlPageSql(String sql, Integer pageNO, Integer pageSize){
        Integer start = (pageNO - 1) * pageSize;
        return sql + " limit " +  start + "," + pageSize;
    }
}
