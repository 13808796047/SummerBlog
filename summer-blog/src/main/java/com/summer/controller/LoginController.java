package com.summer.controller;

import com.summer.entity.R;
import com.summer.entity.User;
import com.summer.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Summer
 * @date 2022/3/21 17:03
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @PostMapping("login")
    public R login(@RequestBody User user){
        return loginService.login(user);
    }
}
