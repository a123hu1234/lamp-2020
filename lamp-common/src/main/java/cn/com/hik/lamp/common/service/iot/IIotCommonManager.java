package cn.com.hik.lamp.common.service.iot;


import cn.com.hik.lamp.common.enums.DeviceModel;
import cn.com.hik.lamp.common.model.iot.IotRequest;
import cn.com.hik.lamp.common.model.iot.IotResponse;

/**
 * @author : hcb
 * @description : 与硬件交互的接口
 * @date : 2020/5/29 10:02
 */
public interface IIotCommonManager {


    /**
     * @param sn 设备sn信息
     * @param deviceModel 设备模型
     * @param t 请求数据
     * @param clazz 返回对象类型
     * @return 返回 响应数据
     * 下发设备命令并接收返回结果
     */
    <S extends IotResponse ,T extends IotRequest> S sendDataStream(String sn, DeviceModel deviceModel, T t,Class<S> clazz);
}
