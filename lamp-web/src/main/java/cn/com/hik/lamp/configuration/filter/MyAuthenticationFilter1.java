package cn.com.hik.lamp.configuration.filter;

import cn.com.hik.lamp.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


/***
 * 访问鉴权 - 每次访问接口都会经过此
 */
@Slf4j
@Component
public class MyAuthenticationFilter1 extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.debug("请求头类型： " + request.getContentType());
        if ((request.getContentType() == null && request.getContentLength() > 0) || (request.getContentType() != null && !request.getContentType().contains(Constants.REQUEST_HEADERS_CONTENT_TYPE))) {
            filterChain.doFilter(request, response);
            return;
        }

        StopWatch stopWatch = new StopWatch();

        try{
            stopWatch.start();
            // 记录请求的消息体
            logRequestBody(request);
            filterChain.doFilter(request,response);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    private void logRequestBody(HttpServletRequest request) {
        if(request != null){
            String body = getBodyJsonStrByReq(request);
            String url = request.getRequestURI().replace("//", "/");
            log.debug("请求url:{}",url);
            log.info("'{}'接收到的请求:{}",url,body);

        }
    }


    private String getBodyJsonStrByReq(HttpServletRequest request){
        String body = "";
        try {
            request.getReader().mark(0);
            body = request.getReader().lines().collect(Collectors.joining());
            request.getReader().reset();
        } catch (IOException e) {
            log.error(e.getMessage(),e);

        }
        return  body;
    }
}
