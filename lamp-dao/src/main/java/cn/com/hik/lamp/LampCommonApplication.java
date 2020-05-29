package cn.com.hik.lamp;

import cn.com.hik.lamp.common.mapper.LampRoleMenuMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/27 9:39
 */
@SpringBootApplication
//@EnableRedisHttpSession
@MapperScan("cn.com.hik.lamp.mapper.common.mapper")
public class LampCommonApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LampCommonApplication.class, args);
        LampRoleMenuMapper lampRoleMenuMapper= context.getBean(LampRoleMenuMapper.class);
        List<String> list = lampRoleMenuMapper.getRolesByUrl("/helloWorld");
        System.out.println(list.toString());
    }
}
