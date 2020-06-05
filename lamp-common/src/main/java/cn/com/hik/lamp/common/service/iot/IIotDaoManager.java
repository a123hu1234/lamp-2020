package cn.com.hik.lamp.common.service.iot;

import cn.com.hik.lamp.common.model.iot.IotDeviceVo;

import java.util.List;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:26
 */
public interface IIotDaoManager<T extends IotDeviceVo> {

    /**
     * 根据sn信息和设备类型查询设备信息
     *
     * @param sn          sn信息
     * @param deviceModle 设备类型
     * @return 设备信息
     */
    IotDeviceVo selectOneBySn(String sn, String deviceModle);


    /**
     * 根据页码，每页大小，设备类型查询设备列表信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param deviceModel 设备类型
     * @return 设备列表
     */
    List<T> selectDeviceList(String pageNum, String pageSize, String deviceModel);


}
