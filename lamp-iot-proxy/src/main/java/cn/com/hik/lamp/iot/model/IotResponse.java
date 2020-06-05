package cn.com.hik.lamp.iot.model;

import cn.com.hik.lamp.common.model.iot.AbsIotResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IotResponse<T> extends AbsIotResponse {
    private T t;
}
