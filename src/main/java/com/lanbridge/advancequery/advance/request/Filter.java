package com.lanbridge.advancequery.advance.request;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @Description 查询
 * @Author wangcheng
 * @Date 2022/10/24 10:31
 */
@Data
public class Filter implements Serializable {


    private static final long serialVersionUID = -8595039898504288071L;

    /**
     * 查询字段
     */
    private String colum;

    /**
     * 查询类型
     */
    private OperateType operateType;

    /**
     * 值
     */
    private Object value;

    public boolean hasValue() {
        return StringUtils.hasLength(getColum()) && (getValue() != null && StringUtils.hasLength(getValue().toString()));
    }

    public String getPreProcessValue(){
        return getValue() == null ? null : getValue().toString();
    }
}
