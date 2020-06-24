package cn.com.hik.lamp.iot.enums;

/**
 * @author : hcb
 * @description : iot提供接口请求配置类
 * @date : 2020/6/23 10:14
 */
public enum  IotUrlEnum {

    IOT_ADD("add","/device/add.do","新增接口"),
    IOT_DELETE("delete","/device/delete.do","设备删除接口"),
    IOT_QUERALL("querAll","/device/querAll.do","查询接口"),
    IOT_QUERY_DATA_STRAEM_RESP("queryDataStreamResp","/device/queryDataStreamResp.do","控制命令查询接口"),
    IOT_SEND_DEVICE_DATA_STREAM("sendDeviceDataStream","/device/sendDeviceDataStream.do","下发数据流接口(不带返回数据)"),
    IOT_SEND_DEVICE_DATA_STREAM_BY_IDENTIFIER("sendDeviceDataStreamByIdentifier","/device/sendDeviceDataStreamByIdentifier.do",
            "带有标识的下发数据流接口"),
    IOT_SEND_DEVICE_DATA_STREAM_RESULT("sendDeviceDataStreamResult","/device/sendDeviceDataStreamResult.do","下发数据流接口并返回对应的结果"),
    IOT_UPDATE("update","/device/update.do","更新接口");



    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private String name;
    private String url;

    @SuppressWarnings({"unused","FieldCanBeLocal"})
    private String desc;

    IotUrlEnum(String name,String url,String desc){
        this.name = name;
        this.url = url;
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }





}
