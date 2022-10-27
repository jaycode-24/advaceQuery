package com.lanbridge.advancequery.advance.support;

import com.lanbridge.advancequery.advance.request.AdvQueryRequest;
import com.lanbridge.advancequery.advance.request.Filter;
import com.lanbridge.advancequery.advance.request.Sorter;
import org.springframework.util.StringUtils;

/**
 * @Description 用于生成jpa sql
 * @Author wangcheng
 * @Date 2022/10/26 17:47
 */
public class JpaNativeSqlBuilder {

    private final static String AND = " and ";
    private final static String OR = " or ";

    public static String buildSql(String sql, AdvQueryRequest advQueryRequest){
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        //返回whereSql
        sb.append(buildWhereSql(advQueryRequest));
        //返回sortSql
        sb.append(buildSortSql(advQueryRequest));
        return sb.toString();
    }

    private static String buildSortSql(AdvQueryRequest advQueryRequest) {
        StringBuilder sb = new StringBuilder();
        if (!advQueryRequest.getSorters().isEmpty()){
            for (Sorter sorter : advQueryRequest.getSorters()) {
                sb.append(sorter.getSortName() + " " + sorter.getSortOrder() + ",");
            }
        }
        return StringUtils.hasLength(sb) ? sb.substring(0,sb.lastIndexOf(",")) : sb.toString();
    }

    private static String buildWhereSql(AdvQueryRequest advQueryRequest) {
        StringBuilder sb = new StringBuilder();
        String link = "";
        if (OR.equals(advQueryRequest.getConditionType())){
            link = OR;
        }else {
            link = AND;
        }
        //用于去除最后一个连接符
        String lastLink = AND;
        //用于判断是否加）
        boolean hasAdvQuery = false;

        if (!advQueryRequest.getFilters().isEmpty()){
            for (Filter filter : advQueryRequest.getFilters()) {
                if (filter.hasValue()){
                    sb.append(" " + filter.getColum() + " = " + filter.getPreProcessValue() + AND);
                }
            }
        }

        if (!advQueryRequest.getAdvFilters().isEmpty()){
            sb.append("(");
            lastLink = link;
            hasAdvQuery = true;
            for (Filter advFilter : advQueryRequest.getAdvFilters()) {
                if (advFilter.hasValue()){
                    sb.append(" " + advFilter.getColum() + " = " + advFilter.getPreProcessValue() + link);
                }
            }
        }
        String whereSql = StringUtils.hasLength(sb.toString()) ? sb.substring(0,sb.lastIndexOf(lastLink)) : sb.toString();
        return hasAdvQuery ? whereSql + ")" : whereSql;
    }

}
