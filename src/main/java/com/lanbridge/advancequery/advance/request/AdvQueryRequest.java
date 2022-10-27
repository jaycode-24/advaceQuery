package com.lanbridge.advancequery.advance.request;

import com.lanbridge.advancequery.advance.support.MybatisAdvQuerySqlBuilder;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 高级查询request
 * @Author wangcheng
 * @Date 2022/10/24 10:29
 */
@Getter
public class AdvQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -4469903719545636789L;
    /**
     * 查询数据
     */
    private List<Filter> filters = new ArrayList<>();

    /**
     * 高级查询：和filters 的主要区别就是 这个连接条件可以是or
     */
    private List<Filter> advFilters = new ArrayList<>();

    /**
     * 排序字段
     */
    private List<Sorter> sorters = new ArrayList<>();

    /**
     * 绑定数据
     * 包括动态拼接的sql 和 数据
     */
    private Map<String,Object> bind = new HashMap<>();

    /**
     * and,or
     */
    private String conditionType;

    /**
     * sql构建器：将filter转换为bind里面的sql
     */
    private transient final MybatisAdvQuerySqlBuilder advQuerySqlBuilder = MybatisAdvQuerySqlBuilder.getInstance();


    public Map<String ,Object> getBind(){
        if (bind.size() == 0){
            //首次获取为空
            bind = advQuerySqlBuilder.createConditionSql(this);
        }
        return bind;
    }

    public List<Sorter> getSorters(){
        return sorters;
    }
}
