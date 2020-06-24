package cn.com.hik.lamp.iot.autoconfig;

/**
 * @author : hcb
 * @description :
 * @date : 2020/6/22 15:56
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "iot")
@Data
public class IotConfig {

    private String iotAddress;
    /**
     * iot连接超时时间
     */
    private  Integer connectTimeOut;

    /**
     * 等待响应超时时间
     */
    private Integer readTimeOut;
}
