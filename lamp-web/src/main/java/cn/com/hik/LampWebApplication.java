package cn.com.hik;

import cn.com.hik.lamp.mapper.UserRoleMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("cn.com.hik.lamp.mapper")
public class LampWebApplication {

    @Resource
    private UserRoleMapper userRoleMapper;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LampWebApplication.class, args);
        UserRoleMapper userROleMapper = context.getBean(UserRoleMapper.class);
        int count = userROleMapper.selectCount(null);
        System.out.println(count);
    }

}
