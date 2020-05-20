package cn.com.hik.lamp.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 9:44
 */
@Slf4j
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private final byte[] body;

    public MultiReadHttpServletRequest(HttpServletRequest request)  {
        super(request);
        body = getBodyString(request).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader()  {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read()  {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    private String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line ;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
           log.error(e.getMessage(),e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
        return sb.toString();
    }


    /**
     * 将前端传递的json数据转换成json字符串 - 前后端分离的情况下使用
     * @param request:
     * @return java.lang.String json字符串
     */
    public String getBodyJsonStrByJson(ServletRequest request){

        StringBuilder json = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            log.error("请求参数转换错误!",e);
        }
        return json.toString();
    }

}
