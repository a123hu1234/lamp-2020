import cn.com.hik.lamp.common.enums.DeviceModel;
import cn.com.hik.lamp.iot.IotSpringBootStart;
import cn.com.hik.lamp.iot.model.DefaultIotRequest;
import cn.com.hik.lamp.iot.model.DefaultIotResponse;
import cn.com.hik.lamp.iot.service.impl.IotCommonManagerImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author : hcb
 * @description :
 * @date : 2020/6/19 16:24
 */
@SpringBootTest(classes = IotSpringBootStart.class)
class IotCommonManagerImplTest {
    private Logger logger = LoggerFactory.getLogger(IotCommonManagerImpl.class);

    @Resource
    private IotCommonManagerImpl iotCommonManager;

    @Test
    void testSendIotData(){
        DefaultIotRequest defaultIotRequest = new DefaultIotRequest();
        defaultIotRequest.setDataStream("{\"code\":\"MX10113013\",\"stream\":\"3301010103200804000000001d99\",\"model\":0,\"sn\":\"4561caf0-a25a-3a39-954e-1d533957be05\"}");
        DefaultIotResponse defaultIotResponse = iotCommonManager.sendDataStream("eaf2e0fb-f358-3dde-b737-84f5974a18a5",DeviceModel.DEVICE_MODEL_ZH,defaultIotRequest,DefaultIotResponse.class);
    }
}
