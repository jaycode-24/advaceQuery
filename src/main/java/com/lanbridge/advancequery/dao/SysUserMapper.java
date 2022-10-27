package com.lanbridge.advancequery.dao;

import com.lanbridge.advancequery.po.SysUser;
import com.lanbridge.advancequery.request.UserRequest;

import java.util.List;

/**
 * @Description TODO
 * @Author wangcheng
 * @Date 2022/10/24 16:40
 */
public interface SysUserMapper {

    List<SysUser> list(UserRequest userRequest);
}
