package com.lanbridge.advancequery.advance.interceptor;

import cn.hutool.core.util.ReflectUtil;
import com.lanbridge.advancequery.advance.request.PageRequest;
import com.lanbridge.advancequery.advance.response.MybatisPage;
import com.lanbridge.advancequery.advance.support.PageSqlUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @Description 分页插件
 * @Author wangcheng
 * @Date 2022/10/25 16:34
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}),
            @Signature(type = ResultSetHandler.class,method = "handleResultSets",args = Statement.class)})
public class PagePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        //1、先看是否拦截的是返回结果集
        if (target instanceof ResultSetHandler){
            return pageListIfNecessary(invocation);
        }
        //2、增加自动分页
        Connection connection = (Connection) invocation.getArgs()[0];
        RoutingStatementHandler statementHandler = (RoutingStatementHandler) target;
        PreparedStatementHandler delegate = (PreparedStatementHandler) ReflectUtil.getFieldValue(statementHandler, "delegate");
        MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate,"mappedStatement");
        BoundSql boundSql = delegate.getBoundSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        PageRequest pageRequest = checkPageRequest(parameterObject);
        if (pageRequest != null){
            processPageSql(connection,parameterObject,parameterMappings,boundSql,mappedStatement,pageRequest.getPageNo(),pageRequest.getPageSize());
        }
        return invocation.proceed();
    }

    /**
     * 分页请求
     * 1、计算总数
     * 2、修改sql
     */
    private void processPageSql(Connection connection,
                                Object parameterObject,
                                List<ParameterMapping> parameterMappings,
                                BoundSql boundSql,
                                MappedStatement mappedStatement,
                                Integer pageNO,
                                Integer pageSize) {
        String originSql = boundSql.getSql();
        String countSql = PageSqlUtil.mysqlCountSql(originSql);

        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, parameterObject);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,parameterObject,countBoundSql);

        //查总数
        nativeCountSql(countBoundSql,connection,parameterHandler,pageSize);

        //分页查数据
        ReflectUtil.setFieldValue(boundSql,"sql",PageSqlUtil.mysqlPageSql(originSql,pageNO,pageSize));
    }

    /**
     * 计算总数
     */
    private void nativeCountSql(BoundSql countBoundSql,Connection connection,ParameterHandler parameterHandler,Integer pageSize) {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(countBoundSql.getSql());
            parameterHandler.setParameters(ps);
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                int totalCount = resultSet.getInt(1);
                MybatisPage.stashResult(totalCount,pageSize);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 检测是否是分页请求
     * @param parameterObject sql原始参数
     * @return PageRequest
     */
    private PageRequest checkPageRequest(Object parameterObject) {
        if (parameterObject instanceof PageRequest &&
                ((PageRequest) parameterObject).getPageNo() != null &&
                ((PageRequest) parameterObject).getPageSize() != null){
            return (PageRequest) parameterObject;
        }
        return null;
    }

    /**
     * 返回结果集预处理
     * @param invocation
     * @return
     */
    private Object pageListIfNecessary(Invocation invocation) throws Exception {
        Object result = invocation.proceed();
        if (MybatisPage.isPageRequest() && result instanceof List){
            return MybatisPage.pageList((List) result);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
