package cn.com.hik.lamp.configuration.login;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/14 14:28
 */
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
