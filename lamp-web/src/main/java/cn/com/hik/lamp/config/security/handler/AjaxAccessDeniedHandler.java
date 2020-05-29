package cn.com.hik.lamp.config.security.handler;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 15:23
 */
@Component
public class AjaxAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        ResponseData<String> responseData = new ResponseData<>();

        responseData.setCode("300");
        responseData.setMessage("Need Authorities!");

        response.getWriter().write(JSON.toJSONString(responseData));
    }
}

