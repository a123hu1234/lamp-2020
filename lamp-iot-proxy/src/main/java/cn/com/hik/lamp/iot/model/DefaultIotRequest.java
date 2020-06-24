package cn.com.hik.lamp.iot.model;

import cn.com.hik.lamp.common.model.iot.IotRequest;
import lombok.Data;
import lombok.ToString;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:08
 */
@Data
@ToString(callSuper = true)
public class DefaultIotRequest implements IotRequest {

    private String dataStream;

    @Override
    public String getDataStream() {
        return dataStream;
    }
}
