package com.lanbridge.advancequery.request;

/**
 * @Description TODO
 * @Author wangcheng
 * @Date 2022/10/25 11:36
 */
public class ResourceListParam {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 母语
     */
    private String motherLanguage;
    /**
     * 兼职类型 （json）
     */
    private String partTimeType;
    /**
     * 任务领域
     */
    private String taskDomain;
    /**
     * 任务子领域
     */
    private String taskSubDomain;
    /**
     * 空闲状态
     */
    private String userStatus;
    /**
     * 接单开关
     */
    private String orderSwitch;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 账户状态（停用，启用）
     */
    private String accountStatus;

    private String nickName;
    private String nationality;
    private String phoneOrEmail;
    /**
     * 代申请
     */
    private String emailApply;

    private Integer pageNo;

    private Integer pageSize;
    private String sort;
    private String desc;
}
