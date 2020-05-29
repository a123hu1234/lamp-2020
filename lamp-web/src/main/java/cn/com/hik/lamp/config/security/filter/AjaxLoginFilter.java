package cn.com.hik.lamp.config.security.filter;

import cn.com.hik.lamp.autoconfig.LampConfig;
import cn.com.hik.lamp.config.security.SelfAuthenticationManager;
import cn.com.hik.lamp.config.security.handler.AjaxAuthenticationFailureHandler;
import cn.com.hik.lamp.config.security.handler.AjaxAuthenticationSuccessHandler;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

/**
 * @author : hcb
 * @description : 如果前端给的是json格式的数据，保存再body中，不能使用默认的
 *                  {@link UsernamePasswordAuthenticationFilter}，
 *                  自定义AjaxLoginFilter完成接收json数据的功能
 * @date : 2020/5/27 16:46
 */
@Component
public class AjaxLoginFilter extends AbstractAuthenticationProcessingFilter {



    public AjaxLoginFilter(LampConfig lampConfig,SelfAuthenticationManager selfAuthenticationManager, AjaxAuthenticationSuccessHandler authenticationSuccessHandler, AjaxAuthenticationFailureHandler authenticationFailureHandler) {

        super(new AntPathRequestMatcher("login", "POST"));
        String loginUrl = lampConfig.getWebConfig().getLoginUrl();
        if(!Strings.isEmpty(loginUrl)) {
            //HttpMethod httpMethod = Optional.of().orElse(HttpMethod.POST);
            this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, "POST"));
        }

         this.setAuthenticationManager(selfAuthenticationManager);
        // 登录成功返回的 JSON 格式数据给前端（否则为 html）
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        //  登录失败返回的 JSON 格式数据给前端（否则为 html）
        this.setAuthenticationFailureHandler(authenticationFailureHandler);


    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{
            LoginInfo loginInfo = getLoginInfo(request);
            UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken(loginInfo.getUsername(),loginInfo.getPassword());
            return  this.getAuthenticationManager().authenticate(token);
           // return
        } catch (IOException e){
            throw new IOException("读取数据失败");
        }
    }

    private LoginInfo getLoginInfo(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        reader.lines().forEach(stringBuilder::append);
        return JSON.parseObject(stringBuilder.toString(), LoginInfo.class);
    }

    @Data
    private static class LoginInfo {

        /**
         * 登录用户名
         */
        private String username;

        /**
         * 登录密码
         */
        private String password;


    }
}
