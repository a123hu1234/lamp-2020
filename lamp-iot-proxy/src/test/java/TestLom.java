import cn.com.hik.lamp.iot.model.IotRequest;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/29 11:13
 */
public class TestLom {

    public static void main(String[] args) {
        IotRequest<String> iotRequest = new IotRequest<>();
        System.out.println(iotRequest.getT());
    }
}
