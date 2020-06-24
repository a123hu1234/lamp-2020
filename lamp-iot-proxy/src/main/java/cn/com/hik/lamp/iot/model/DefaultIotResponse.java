package cn.com.hik.lamp.iot.model;

import cn.com.hik.lamp.common.model.iot.IotResponse;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:09
 */
@Data
@ToString(callSuper = true)
public class DefaultIotResponse implements IotResponse {

    private String body;

    private int code;

    private String msg;

    private String timestamp;

    private String result;




}
