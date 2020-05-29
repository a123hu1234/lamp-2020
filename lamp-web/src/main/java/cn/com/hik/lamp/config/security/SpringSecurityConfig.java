package cn.com.hik.lamp.config.security;

import cn.com.hik.lamp.autoconfig.LampConfig;
import cn.com.hik.lamp.config.security.authertic.AjaxAuthenticationEntryPoint;
import cn.com.hik.lamp.config.security.authertic.SelfAuthenticationProvider;
import cn.com.hik.lamp.config.security.filter.AjaxLoginFilter;
import cn.com.hik.lamp.config.security.handler.AjaxAccessDeniedHandler;
import cn.com.hik.lamp.config.security.handler.AjaxLogoutSuccessHandler;
import cn.com.hik.lamp.config.security.session.AjaxInvalidSessionStrategy;
import cn.com.hik.lamp.config.security.url.UrlAccessDecisionManager;
import cn.com.hik.lamp.config.security.url.UrlFilterInvocationSecurityMetadataSource;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    AjaxAuthenticationEntryPoint authenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）


    @Resource
    AjaxLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Resource
    AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Resource
    AjaxInvalidSessionStrategy ajaxInvalidSessionStrategy;

    @Resource
    SelfAuthenticationProvider provider; // 自定义安全认证

    /**
     * 获取访问url所需要的角色信息
     */
    @Resource
    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    /**
     * 认证权限处理 - 将上面所获得角色权限与当前登录用户的角色做对比，如果包含其中一个角色即可正常访问
     */
    @Resource
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Resource
    AjaxLoginFilter ajaxLoginFilter;

    @Resource
    LampConfig lampConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        // 加入自定义的安全认证
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable()

                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()

                .anyRequest()
                .authenticated()// 其他 url 需要身份认证
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionStrategy(ajaxInvalidSessionStrategy)
                .maximumSessions(1);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据

        http.addFilterAt(ajaxLoginFilter, UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 忽略session校验
     * @param web web

     */
    @Override
    public void configure(WebSecurity web){
        String getIgoreUrl =  lampConfig.getWebConfig().getGetIgoreUrl();
        if(!Strings.isEmpty(getIgoreUrl)){
            String[] urls = getIgoreUrl.split(",");
            web.ignoring().mvcMatchers(HttpMethod.GET,urls);
        }

        String postIgoreUrl =  lampConfig.getWebConfig().getPostIgoreUrl();
        if(!Strings.isEmpty(postIgoreUrl)){
            String[] urls = getIgoreUrl.split(",");
            web.ignoring().mvcMatchers(HttpMethod.POST,urls);
        }
    }
}

