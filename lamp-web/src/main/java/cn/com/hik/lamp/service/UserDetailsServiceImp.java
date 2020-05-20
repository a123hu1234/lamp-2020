package cn.com.hik.lamp.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 15:34
 */
@Component
public class UserDetailsServiceImp implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User("123","123", Collections.singletonList(new SimpleGrantedAuthority("admin")));
    }
}
