package cn.com.hik.lamp.config;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 16:57
 */
@Component
public class AjaxSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        ResponseData<String> responseBody = new ResponseData<>();

        responseBody.setCode("202");
        responseBody.setMessage("invalid session !");
      ///  response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
