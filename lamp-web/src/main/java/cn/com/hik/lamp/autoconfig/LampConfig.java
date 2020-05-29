package cn.com.hik.lamp.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/28 14:06
 */
@Component
@ConfigurationProperties(prefix = "lamp")
@Data
public class LampConfig {

    /***
     * 自定义web相关配置
     */
    private WebConfig webConfig;

    @SuppressWarnings("WeakerAccess")
    @Data
    public static class  WebConfig{

        /***
         * 忽略post请求的url
         *
         */
        private String postIgoreUrl;

        /**
         * 忽略get请求的url
         */
        private String getIgoreUrl;
        /**
         * 自定义登录请求，默认login
         */
        private String loginUrl;

        /**
         * 是否忽略swaager发起的请求的权限校验
         */
        private Boolean igoreSwaggerRequest;

    }
}
