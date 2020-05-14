package cn.com.hik.lamp.configuration;

import cn.com.hik.lamp.configuration.filter.AdminAuthenticationProcessingFilter;
import cn.com.hik.lamp.configuration.filter.MyAuthenticationFilter;
import cn.com.hik.lamp.configuration.filter.MyAuthenticationFilter1;
import cn.com.hik.lamp.configuration.login.LoginAuthenticationEntryPoint;
import cn.com.hik.lamp.configuration.url.UrlAccessDecisionManager;
import cn.com.hik.lamp.configuration.url.UrlAccessDeniedHandler;
import cn.com.hik.lamp.configuration.url.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {



    private final MyAuthenticationFilter myAuthenticationFilter;

    @Resource
    private MyAuthenticationFilter1 myAuthenticationFilter1;

    private final LoginAuthenticationEntryPoint loginAuthenticationEntryPoint;


    private final UrlAccessDeniedHandler urlAccessDeniedHandler;


    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;


    private final UrlAccessDecisionManager urlAccessDecisionManager;


    private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;


    public SpringSecurityConfig(MyAuthenticationFilter myAuthenticationFilter, LoginAuthenticationEntryPoint loginAuthenticationEntryPoint
            , UrlAccessDeniedHandler urlAccessDeniedHandler, UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource
            , UrlAccessDecisionManager urlAccessDecisionManager, AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter) {
        this.myAuthenticationFilter = myAuthenticationFilter;
        this.loginAuthenticationEntryPoint = loginAuthenticationEntryPoint;
        this.urlAccessDecisionManager = urlAccessDecisionManager;
        this.urlAccessDeniedHandler = urlAccessDeniedHandler;
        this.urlFilterInvocationSecurityMetadataSource = urlFilterInvocationSecurityMetadataSource;
        this.adminAuthenticationProcessingFilter = adminAuthenticationProcessingFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();

        // 禁用CSRF 开启跨域
        http.csrf().disable().cors();

        // 未登录认证异常
        http.exceptionHandling().authenticationEntryPoint(loginAuthenticationEntryPoint);
        // 登录过后访问无权限的接口时自定义403响应内容
        // http.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);

        // url权限认证处理
        registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(urlAccessDecisionManager);
                return o;
            }
        });

        // 不创建会话 - 即通过前端传token到后台过滤器中验证是否存在访问权限
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 标识只能在 服务器本地ip[127.0.0.1或localhost] 访问 `/home` 这个接口，其他ip地址无法访问
        //registry.antMatchers("/home").hasIpAddress("127.0.0.1");


        // 允许匿名的url - 可理解为放行接口 - 除配置文件忽略url以外，其它所有请求都需经过认证和授权
        // for (String url : myProperties.getAuth().getIgnoreUrls()) {
        //     registry.antMatchers(url).permitAll();
        //  }
        registry.antMatchers("/login").permitAll();
        // OPTIONS(选项)：查找适用于一个特定网址资源的通讯选择。 在不需执行具体的涉及数据传输的动作情况下， 允许客户端来确定与资源相关的选项以及 / 或者要求， 或是一个服务器的性能
        registry.antMatchers(HttpMethod.OPTIONS, "/**").denyAll();

        // 自动登录 - cookie储存方式
        registry.and().rememberMe();

        // 其余所有请求都需要认证
        registry.anyRequest().authenticated();

        // 防止iframe 造成跨域
        registry.and().headers().frameOptions().disable();

        // 自定义过滤器在登录时认证用户名、密码
        http
                .addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(myAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(myAuthenticationFilter1,BasicAuthenticationFilter.class);
    }

//    /**
//     * 忽略拦截url或静态资源文件夹 - web.ignoring(): 会直接过滤该url - 将不会经过Spring Security过滤器链
//     * http.permitAll(): 不会绕开springsecurity验证，相当于是允许该路径通过
//     *
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.GET,
//                "/favicon.ico",
//                "/**/*.png",
//                "/**/*.ttf",
//                "/*.html",
//                "/**/*.css",
//                "/**/*.js");
//    }
}
