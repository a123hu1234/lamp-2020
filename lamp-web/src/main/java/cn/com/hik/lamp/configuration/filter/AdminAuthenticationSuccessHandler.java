package cn.com.hik.lamp.configuration.filter;

import cn.com.hik.lamp.common.model.ResponseData;
import cn.com.hik.lamp.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 16:28
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
      //  User user = new User();
       // SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
       // user.setToken(securityUser.getCurrentUserInfo().getToken());
        ResponseUtils.out(response, ResponseUtils.RESPONSE_DATA_SUCCESS);
    }
}