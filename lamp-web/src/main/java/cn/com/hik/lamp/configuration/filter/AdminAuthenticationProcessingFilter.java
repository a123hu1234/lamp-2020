package cn.com.hik.lamp.configuration.filter;

import cn.com.hik.lamp.common.constants.Constants;
import cn.com.hik.lamp.configuration.login.AdminAuthenticationFailureHandler;
import cn.com.hik.lamp.configuration.login.CusAuthenticationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AdminAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {



    /**
     * @param authenticationManager:             认证管理器
     * @param adminAuthenticationSuccessHandler: 认证成功处理
     * @param adminAuthenticationFailureHandler: 认证失败处理
     */
    protected AdminAuthenticationProcessingFilter(CusAuthenticationManager authenticationManager, AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler, AdminAuthenticationFailureHandler adminAuthenticationFailureHandler) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(adminAuthenticationSuccessHandler);
        this.setAuthenticationFailureHandler(adminAuthenticationFailureHandler);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (request.getContentType() == null || !request.getContentType().contains(Constants.REQUEST_HEADERS_CONTENT_TYPE)) {
            throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest;
        log.info(getBodyJsonStrByReq(request));
        authRequest = new UsernamePasswordAuthenticationToken("test", "test", null);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String getBodyJsonStrByReq(HttpServletRequest request){
        String body = "";
        try {
            body = request.getReader().lines().collect(Collectors.joining());
        } catch (IOException e) {
            log.error(e.getMessage(),e);

        }
        return  body;
    }
}
