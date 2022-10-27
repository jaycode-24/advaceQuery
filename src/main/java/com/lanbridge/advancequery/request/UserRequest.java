package com.lanbridge.advancequery.request;

import com.lanbridge.advancequery.advance.request.AdvQueryRequest;
import lombok.Data;

/**
 * @Description TODO
 * @Author wangcheng
 * @Date 2022/10/24 16:39
 */
@Data
public class UserRequest extends AdvQueryRequest {
    private static final long serialVersionUID = -4873525395678224356L;

    private String sex;

}

