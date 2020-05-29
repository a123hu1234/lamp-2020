package cn.com.hik.lamp.service;

import cn.com.hik.lamp.common.entity.LampUser;
import cn.com.hik.lamp.common.service.system.ILampUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/15 15:34
 */
@Component
public class UserDetailsServiceImp implements UserDetailsService {

    @Resource
    private ILampUserService lampUserServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) {

        LampUser lampUser = new LampUser();
        lampUser.setLoginName(username);
        LampUser lampUser1 = lampUserServiceImpl.getOne(Wrappers.lambdaQuery(lampUser));

        if(Objects.isNull(lampUser1)){
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new User(lampUser1.getLoginName(),lampUser1.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(String.valueOf(lampUser1.getRoleId()))));
    }
}
