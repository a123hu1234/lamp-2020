package cn.com.hik.lamp.config;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 16:43
 */
@Component
public class AjaxInvalidSessionStrategy implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResponseData<String> responseBody = new ResponseData<>();

        responseBody.setCode("202");
        responseBody.setMessage("invalid session !");
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
