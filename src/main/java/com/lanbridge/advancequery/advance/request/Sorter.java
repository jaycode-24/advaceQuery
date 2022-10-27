package com.lanbridge.advancequery.advance.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 排序
 * @Author wangcheng
 * @Date 2022/10/24 10:31
 */
@Data
public class Sorter implements Serializable {
    private static final long serialVersionUID = 2644457966608030845L;

    private String sortName;

    private String sortOrder;


}
