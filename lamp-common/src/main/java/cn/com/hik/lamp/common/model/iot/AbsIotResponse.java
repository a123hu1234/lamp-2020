package cn.com.hik.lamp.common.model.iot;

import lombok.Data;

import java.util.Date;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 10:19
 */
@Data
public abstract class AbsIotResponse {
    private String code;
    private String msg;
    private Date timestamp;
}
