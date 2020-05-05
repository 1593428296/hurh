package com.hurh.crowd.mvc.config;

import com.hurh.crowd.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 表示当前类是一个配置类
@Configuration
// 启用web环境下权限控制功能
@EnableWebSecurity
// 启用全局方法权限控制功能，并设置prePostEnabled = true，保证@PreAuthorize、@PostAuthprity、@PreFilter、@PostFilter 生效
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class CrowdfundingSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
                .authorizeRequests()	// 对请求进行授权
                .antMatchers("/admin/to/login/page.html")	// 针对登录页进行设置
                .permitAll()			// 无条件访问
                .antMatchers("/bootstrap/**")	// 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/crowd/**")       // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/css/**")         // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/fonts/**")       // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/img/**")         // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/jquery/**")      // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/layer/**")       // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/script/**")      // 针对静态资源进行设置，无条件访问
                .permitAll()                    // 针对静态资源进行设置，无条件访问
                .antMatchers("/ztree/**")       // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/admin/getPageBean.html")	// 针对分页显示Admin数据设定访问控制
                 .hasRole("经理")					// 要求具备经理角色
                //.access("hasRole('经理') OR hasAuthority('user:get')")	// 要求具备“经理”角色和“user:get”权限二者之一
                .anyRequest()					// 其他任意请求
                .authenticated()				// 认证后访问
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response,
                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        request.setAttribute("exception", new Exception(Constant.MESSAGE_ACCESS_DENIDE));
                        request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
                    }
                })
                .and()
                .csrf()							// 防跨站请求伪造功能
                .disable()						// 禁用
                .formLogin()					// 开启表单登录的功能
                .loginPage("/admin/login.html")                             // 指定登录页面、
                .loginProcessingUrl("/security/loginCheck.html")            // 指定处理登录请求的页面
                .defaultSuccessUrl("/admin/mainPage.html")                 // 指定登录成功后的页面
                .usernameParameter("loginAcct")                             // 指定账号的请求参数名
                .passwordParameter("userPswd")
                .and()
                .logout()
                .logoutUrl("/security/logOut.html")                                                  // 指定退出登录的URL
                .logoutSuccessUrl("/admin/login.html")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)


        ;

      /*  // 使用内存登录的模式测试是否能登录
        builder.
                inMemoryAuthentication()
                .withUser("hurh")
                .password("111111")
                .roles("ADMIN")
        ;*/

    }
}
