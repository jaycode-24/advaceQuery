package com.lanbridge.advancequery.po;

import lombok.Data;

/**
 * 资源列表返回实体
 *
 * @author XiangChao
 * @date 2019/9/2
 */
@Data
public class ResourceListResponse {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户编号
     */
    private String userCode;
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
     * 兼职类型
     */
    private String skills;

    /**
     * 母语
     */
    private String motherLanguage;
    /**
     * source 语言对/语种
     */
    private String sourceLanguageName;
    /**
     * target 语言对/语种
     */
    private String targetLanguageName;
    /**
     * 擅长领域
     */
    private String areaName;
    /**
     * 擅长子领域
     */
    private String subAreaName;
    /**
     * 等级
     */
    private String levelName;
    /**
     * 身份认证
     */
    private String certificatePassed;
    /**
     * 账号认证
     */
    private String settleCertificatePassed;
    /**
     * 接单数
     */
    private String orderReived;
    /**
     * 投诉次数
     */
    private String numberOfComplaints;
    /**
     * 接单开关
     */
    private String receipted;
    /**
     * 用户状态
     */
    private String translatorIdleStatus;
    /**
     * 译侠值
     */
    private String totalScore;
    /**
     * 任务平均分
     */
    private String orderAverageScore;
    /**
     * 常住地址
     */
    private String permanentAddress;

    private String emailApply;
    /**
     * 账号状态
     */
    private String isEnabled;

    private String gmtCreate;

    private String nickName;
    private String nationality;
    private String rejectNum;
    private String beRejectNum;
    private String lastLoginTime;
    private String phoneOrEmail;

    /**
     * 是否朱注销中：1是；0不是
     */
    private String isCanceling;

    private String last;

}
