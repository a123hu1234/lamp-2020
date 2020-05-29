package cn.com.hik.lamp.config.security.authertic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 15:26
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Resource
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)  {
        String userName = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials(); // 这个是表单中输入的密码；
        UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password,userInfo.getPassword())){
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }

        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

