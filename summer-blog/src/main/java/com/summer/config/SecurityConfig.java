package com.summer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Summer
 * @date 2022/3/21 17:11
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
   public AuthenticationManager authenticationManagerBean() throws Exception{
       return super.authenticationManagerBean();
   }
   @Override
   protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable()// 关闭csrf
                // 不通过session获取SecurityContext
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口，允许匿名访问
                .antMatchers("/login").anonymous()
               // 除上面外的所有请求全部不需要认证即可访问
                .anyRequest().permitAll();
        http.logout().disable();
        // 允许跨域
        http.cors();
   }
   @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
   }
}
