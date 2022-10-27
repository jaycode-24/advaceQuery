package com.lanbridge.advancequery.advance.support;

import cn.hutool.core.util.StrUtil;
import com.lanbridge.advancequery.advance.request.AdvQueryRequest;
import com.lanbridge.advancequery.advance.request.Filter;
import com.lanbridge.advancequery.advance.request.Sorter;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description sql构建器：主要用于将前端参数转换为sql语句
 * @Author wangcheng
 * @Date 2022/10/24 10:49
 */
public class MybatisAdvQuerySqlBuilder {

    private final static MybatisAdvQuerySqlBuilder INSTANCE = new MybatisAdvQuerySqlBuilder();

    public static MybatisAdvQuerySqlBuilder getInstance(){
        return INSTANCE;
    }

    public final static String AND = " and ";
    public final static String OR = " or ";

    /**
     * 构建bind
     * @param advQueryRequest 高级查询对象
     * @return bind
     */
    public Map<String, Object> createConditionSql(AdvQueryRequest advQueryRequest) {
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
        StringBuilder sql = new StringBuilder();
        Map<String,Object> bind = new HashMap<>();
        Integer i = 0;
        //普通查询
        if (!advQueryRequest.getFilters().isEmpty()){
            for (Filter filter : advQueryRequest.getFilters()) {
                if (filter.hasValue()){
                    String paramPrefix = "p" + (i++);
                    //a.order_id = #{bind.p0}
                    String sqlStatement = filter.getOperateType().getSqlStatementHandle().createCondition(filter,bind,paramPrefix);
                    sql.append(sqlStatement + AND);
                }
            }
        }

        //高级查询
        if (!advQueryRequest.getAdvFilters().isEmpty()){
            lastLink = link;
            sql.append("(");
            hasAdvQuery = true;
            for (Filter advFilter : advQueryRequest.getAdvFilters()) {
                if (advFilter.hasValue()){
                    String paramPrefix = "p" + (i++);
                    //a.order_id = #{bind.p0}
                    String sqlStatement = advFilter.getOperateType().getSqlStatementHandle().createCondition(advFilter,bind,paramPrefix);
                    sql.append(sqlStatement + link);
                }
            }
        }
        String advSql = StringUtils.hasLength(sql.toString()) ? sql.substring(0,sql.lastIndexOf(lastLink)) : sql.toString();
        bind.put("hasQuery", StringUtils.hasLength(advSql));
        bind.put("sql",hasAdvQuery ? advSql + ")" : advSql);
        String sortSql = createSort(advQueryRequest.getSorters());
        bind.put("hasSort",StringUtils.hasLength(sortSql));
        bind.put("sort",sortSql);
        return bind;
    }

    private String createSort(List<Sorter> sorters) {
        StringBuilder sb = new StringBuilder();
        if (!sorters.isEmpty()){
            for (Sorter sorter : sorters) {
                sb.append(sorter.getSortName() + " " + sorter.getSortOrder() + ",");
            }
        }
        return StringUtils.hasLength(sb) ? sb.substring(0,sb.lastIndexOf(",")) : sb.toString();
    }


}
