package cn.com.hik.lamp.config.security.handler;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 15:13
 */
@Component
@Slf4j
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ResponseData<String> responseBody = new ResponseData<>();

        responseBody.setCode("-1");
        responseBody.setMessage(e.getMessage());
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));

    }
}
