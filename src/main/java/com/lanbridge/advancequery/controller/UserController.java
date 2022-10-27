package com.lanbridge.advancequery.controller;

import com.lanbridge.advancequery.dao.SysUserMapper;
import com.lanbridge.advancequery.po.ResourceListResponse;
import com.lanbridge.advancequery.po.SysUser;
import com.lanbridge.advancequery.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author wangcheng
 * @Date 2022/10/24 16:37
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    SysUserMapper sysUserMapper;

    @PostMapping("/list")
    public List<SysUser> list(@RequestBody UserRequest userRequest){
        return sysUserMapper.list(userRequest);
    }


    /*@PostMapping("/getAllUser")
    public ResponseEntity<List<ResourceListResponse>> getAllUser(@RequestBody ){

    }*/
}
