package cn.com.hik.lamp.configuration.login;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 16:24
 */
@Component
public class CusAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return authentication;
    }
}
