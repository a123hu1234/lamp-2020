package cn.com.hik.lamp.common.enums;

/**
 * @author : hcb
 * @description :
 * @date : 2020/6/23 8:48
 */
public enum DeviceModel {

    DEVICE_MODEL_PDU("MX10113013","电源控制器模型类型"),
    DEVICE_MODEL_ZH("MX00000000","智盒模型类型");

    DeviceModel(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
