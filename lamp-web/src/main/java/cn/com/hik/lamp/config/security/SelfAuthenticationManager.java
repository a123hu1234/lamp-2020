package cn.com.hik.lamp.config.security;

import cn.com.hik.lamp.config.security.authertic.SelfAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/27 17:33
 */
@Component
public class SelfAuthenticationManager implements AuthenticationManager {

    private final SelfAuthenticationProvider selfAuthenticationProvider;

    public SelfAuthenticationManager(SelfAuthenticationProvider selfAuthenticationProvider) {
        this.selfAuthenticationProvider = selfAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        Authentication result = selfAuthenticationProvider.authenticate(authentication);
        if (Objects.nonNull(result)) {
            return result;
        }
        throw new ProviderNotFoundException("认证失败!");
    }
}