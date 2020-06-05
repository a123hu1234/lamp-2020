package cn.com.hik.lamp.common.model.iot;

import lombok.Data;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 10:20
 */
@Data
public abstract class AbsIotRequest {
    private String sn;
    private String deviceModel;
}
