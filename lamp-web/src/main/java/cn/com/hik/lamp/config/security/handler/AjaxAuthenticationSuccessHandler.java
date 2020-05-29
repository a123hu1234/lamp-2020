package cn.com.hik.lamp.config.security.handler;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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
 * @date : 2020/5/15 15:18
 */
@Component
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseData<String> responseBody = new ResponseData<>();

        responseBody.setCode("200");
        responseBody.setMessage("Login success!");
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
