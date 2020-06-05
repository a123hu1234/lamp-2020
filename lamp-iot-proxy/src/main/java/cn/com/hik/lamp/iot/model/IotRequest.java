package cn.com.hik.lamp.iot.model;

import cn.com.hik.lamp.common.model.iot.AbsIotRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:08
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IotRequest<T> extends AbsIotRequest {
    private T t;
}
