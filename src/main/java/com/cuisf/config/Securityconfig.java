package com.cuisf.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);

        //首页所有人可以访问  ，功能页  只有对应权限的人才可以访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()  //首页所有人可以访问
                .antMatchers("/level1/**").hasRole("vip1") //拥有vip1 角色的权限  可以查看到level1下的功能
                .antMatchers("/level2/**").hasRole("vip1") //拥有vip1 角色的权限  可以查看到level2下的功能
                .antMatchers("/level3/**").hasRole("vip1"); //拥有vip1 角色的权限  可以查看到level3下的功能

        //没有权限 跳转到登录界面  定制登录界面
        http.formLogin().loginPage("/toLogin");

        //登出失败  的情况 可能是 csrf 的问题
        //get 请求不安全  ，防止网站攻击   关闭csrf功能  springboot 是默认开启的
        http.csrf().disable();

        //开启注销功能
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能 可以自定义前端参数名称remember
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("cuisf").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
