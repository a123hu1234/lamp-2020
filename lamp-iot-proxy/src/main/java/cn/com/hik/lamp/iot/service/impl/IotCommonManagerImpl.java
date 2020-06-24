package cn.com.hik.lamp.iot.service.impl;

import cn.com.hik.lamp.common.enums.DeviceModel;
import cn.com.hik.lamp.common.model.iot.IotRequest;
import cn.com.hik.lamp.common.model.iot.IotResponse;
import cn.com.hik.lamp.common.service.iot.AbstractIotCommonManager;
import cn.com.hik.lamp.iot.autoconfig.IotConfig;
import cn.com.hik.lamp.iot.enums.IotUrlEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:03
 */
@Service
public class IotCommonManagerImpl extends AbstractIotCommonManager {

    private Logger logger = LoggerFactory.getLogger(IotCommonManagerImpl.class);

    private RestTemplate restTemplate;


    private IotConfig iotConfig;

    @Autowired
    public   IotCommonManagerImpl(IotConfig iotConfig){
        this.iotConfig = iotConfig;
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new FastJsonHttpMessageConverter());
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(iotConfig.getConnectTimeOut());
        requestFactory.setReadTimeout(iotConfig.getReadTimeOut());
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            private Logger logger = LoggerFactory.getLogger("responseErrorHandler");
            @Override
            public boolean hasError(ClientHttpResponse response)  {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) {
                logger.error("handlerError:{}",response);
            }
        });
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setMessageConverters(messageConverters);



    }

    @Override
    public <S extends IotResponse, T extends IotRequest> S sendDataStream
            (String sn, DeviceModel deviceModel,T t,Class<S> clazz) {

        try{
            String reqStr = "?sn={sn}&deviceModel={deviceModel}&dataStream={dataStream}";
            Map<String, String> variables = new HashMap<>();
            variables.put("sn", sn);
            variables.put("dataStream", t.getDataStream());
            variables.put("deviceModel", deviceModel.getCode());
            ParameterizedTypeReference<String> typeReference = new ParameterizedTypeReference<String>(){};
            ResponseEntity<String> responseEntity = restTemplate.exchange(iotConfig.getIotAddress() +
                    IotUrlEnum.IOT_SEND_DEVICE_DATA_STREAM_RESULT.getUrl()+reqStr, HttpMethod.POST, null, typeReference, variables);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                //去除特殊符号"\r"
                String body = Optional.ofNullable(responseEntity.getBody()).orElse("").replaceAll("\r","");

                S s   = JSON.parseObject(body,clazz);

                logger.info("接收到iot响应信息:{}",s);
                return s;
            }
            logger.warn("iot响应异常:code={},codeValue={}",responseEntity.getStatusCode(),responseEntity.getStatusCodeValue());
        }catch (Exception e){
            logger.error("系统异常",e);
        }
        return null;
    }
}
