package com.summer.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Summer
 * @date 2022/3/21 19:42
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    //主键
    private Long id;
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //邮箱
    private String email;
}
