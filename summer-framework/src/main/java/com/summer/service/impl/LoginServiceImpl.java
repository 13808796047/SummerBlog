package com.summer.service.impl;

import com.summer.entity.LoginUser;
import com.summer.entity.R;
import com.summer.entity.User;
import com.summer.entity.vo.LoginVo;
import com.summer.entity.vo.UserInfoVo;
import com.summer.service.LoginService;
import com.summer.utils.BeanCopyUtils;
import com.summer.utils.JwtUtil;
import com.summer.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Summer
 * @date 2022/3/21 17:09
 */
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;
    @Override
    public R login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取Userid生成Token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        // 把token和userinfo封装返回
        // 把User转换成userInfo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        LoginVo vo = new LoginVo(jwt,userInfoVo);
        return R.okResult(vo);
    }
}
