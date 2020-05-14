package cn.com.hik.lamp.configuration.url;

import cn.com.hik.lamp.common.constants.Constants;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 17:05
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
