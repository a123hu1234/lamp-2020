package cn.com.hik.lamp.common.service.iot;


import cn.com.hik.lamp.common.model.iot.AbsIotRequest;
import cn.com.hik.lamp.common.model.iot.AbsIotResponse;

/**
 * @author : hcb
 * @description : 与硬件交互的接口
 * @date : 2020/5/29 10:02
 */
public interface IIotCommonManager<T extends AbsIotRequest,S extends AbsIotResponse> {


    /**
     *
     * @param  iotRequest 请求信息
     * @return 响应结果
     */
    S sendDataStream(T iotRequest);
}
