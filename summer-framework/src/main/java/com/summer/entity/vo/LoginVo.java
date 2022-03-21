package com.summer.entity.vo;

import com.summer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Summer
 * @date 2022/3/21 19:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private String token;
    private UserInfoVo userInfo;
}
