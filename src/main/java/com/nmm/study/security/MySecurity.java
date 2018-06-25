package com.nmm.study.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 修改安全配置信息
 */
@Configuration
public class MySecurity extends WebSecurityConfigurerAdapter {
    /**
     * 登录信息
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MyUserDetailService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略静态资源
        web.ignoring().mvcMatchers("**/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加验证
        http.authorizeRequests()
                .mvcMatchers("/login/**").permitAll()
                .mvcMatchers("/hello/**").hasAnyAuthority("ADMIN")
                .mvcMatchers("/sayHello").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and();
    }
}
