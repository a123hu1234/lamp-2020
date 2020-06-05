package cn.com.hik.lamp.common.service.iot;

import cn.com.hik.lamp.common.model.iot.AbsIotRequest;
import cn.com.hik.lamp.common.model.iot.AbsIotResponse;
import cn.com.hik.lamp.common.model.iot.IotDeviceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 14:09
 */
public abstract class AbsIotCommonManager<S extends AbsIotRequest,T extends AbsIotResponse,C extends IotDeviceVo> implements IIotCommonManager<S, T>,IIotDaoManager<C>{

    private Logger logger = LoggerFactory.getLogger(AbsIotCommonManager.class);

    @Override
    public T sendDataStream(S iotRequest) {
        logger.error("未定义sendDataStream方法");
        return null;
    }

    @Override
    public C selectOneBySn(String sn, String deviceModle) {
        logger.error("为定义selectOnBySn方法");
        return null;
    }

    @Override
    public List<C> selectDeviceList(String pageNum, String pageSize, String deviceModel) {

        logger.error("未定义selectDeviceList方法");
        return Collections.emptyList();
    }
}
