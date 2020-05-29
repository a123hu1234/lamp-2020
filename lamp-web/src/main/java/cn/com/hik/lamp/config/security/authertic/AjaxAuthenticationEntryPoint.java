package cn.com.hik.lamp.config.security.authertic;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 15:19
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseData<String> responseData = new ResponseData<>();

        responseData.setCode("000");
        responseData.setMessage("Need Authorities!");

        response.getWriter().write(JSON.toJSONString(responseData));
    }
}

