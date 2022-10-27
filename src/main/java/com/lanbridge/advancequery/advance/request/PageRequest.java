package com.lanbridge.advancequery.advance.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 分页查询请求
 * @Author wangcheng
 * @Date 2022/10/24 10:40
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -6049850967114110616L;
    /**
     * 页号
     */
    private Integer pageNo;

    /**
     * 分页大小
     */
    private Integer pageSize;
}
