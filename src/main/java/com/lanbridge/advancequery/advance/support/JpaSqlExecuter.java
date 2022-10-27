package com.lanbridge.advancequery.advance.support;

import com.lanbridge.advancequery.advance.request.AdvQueryRequest;
import com.lanbridge.advancequery.advance.response.PageList;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Description jpa sql执行器
 * @Author wangcheng
 * @Date 2022/10/26 19:28
 */
public class JpaSqlExecuter {

    @PersistenceContext
    private EntityManager entityManager;


    public <T> List<T> executeSql(String sql, AdvQueryRequest advQueryRequest, Class<T> cls){
        String advSql = JpaNativeSqlBuilder.buildSql(sql, advQueryRequest);
        String countSql = PageSqlUtil.mysqlCountSql(advSql);
        Query query = entityManager.createNativeQuery(countSql);
        Integer totalCount = (Integer) query.getSingleResult();

        String pageSql = PageSqlUtil.mysqlPageSql(advSql, advQueryRequest.getPageNo(), advQueryRequest.getPageSize());
        Query pageQuery = entityManager.createNativeQuery(pageSql);
        pageQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(cls));
        List<T> resultList = pageQuery.getResultList();
        return new PageList<>(resultList, totalCount, (totalCount - 1) / advQueryRequest.getPageSize() + 1);
    }
}
