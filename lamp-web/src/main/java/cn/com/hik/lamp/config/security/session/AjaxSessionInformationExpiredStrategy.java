package cn.com.hik.lamp.config.security.session;

import cn.com.hik.lamp.common.model.ResponseData;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 16:57
 */
@Component
public class AjaxSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event)  {
        ResponseData<String> responseBody = new ResponseData<>();

        responseBody.setCode("202");
        responseBody.setMessage("session已失效");
      ///  response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
