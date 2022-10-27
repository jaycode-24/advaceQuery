package com.lanbridge.advancequery.po;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description TODO
 * @Author wangcheng
 * @Date 2022/10/24 16:38
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 3168839651280608430L;


    private String id;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 更新时间
     */
    private Timestamp gmtUpdate;
    /**
     * 是否删除 (0:false  1:true)
     */
    private Boolean deleted;
    /**
     * 最后登录时间
     */
    private Timestamp lastLoginTime;
    /**
     * 部门名称
     */
    private String department;
    /**
     * 编号
     */
    private String userCode;
    /**
     * 名称
     */
    private String userName;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 地址
     */
    private String address;


}
