package cn.com.hik.lamp.utils;

import cn.com.hik.lamp.common.model.ResponseData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 14:40
 */
@Slf4j
public class ResponseUtils {


    public static final ResponseData<String> RESPONSE_DATA_TIME_OUT = new ResponseData<>("-1","time out","");
    public static final ResponseData<String> RESPONSE_DATA_SUCCESS = new ResponseData<>("200","success","");
    public static final ResponseData<String> RESPONSE_DATA_FAIL = new ResponseData<>("-1","fail","");

    public static void out(ServletResponse response, ResponseData responseData){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(responseData));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
