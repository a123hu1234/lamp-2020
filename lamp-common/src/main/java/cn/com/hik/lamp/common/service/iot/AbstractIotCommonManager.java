package cn.com.hik.lamp.common.service.iot;

import cn.com.hik.lamp.common.enums.DeviceModel;
import cn.com.hik.lamp.common.model.iot.IotRequest;
import cn.com.hik.lamp.common.model.iot.IotResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 14:09
 */
public abstract class AbstractIotCommonManager implements IIotCommonManager{

    private Logger logger = LoggerFactory.getLogger(AbstractIotCommonManager.class);

    @Override
    public <S extends IotResponse, T extends IotRequest> S sendDataStream
            (String sn, DeviceModel deviceModel,T t,Class<S> clazz) {
        logger.error("未定义sendDataStream(sn,deviceModel,t)方法");
        return null;
    }
}
