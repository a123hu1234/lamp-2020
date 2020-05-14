package cn.com.hik.lamp.configuration.login;

import cn.com.hik.lamp.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 认证权限入口 - 未登录的情况下访问所有接口都会拦截到此
 *
 */
@Slf4j
@Component
public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.out(response, ResponseUtils.RESPONSE_DATA_TIME_OUT);
    }
}
