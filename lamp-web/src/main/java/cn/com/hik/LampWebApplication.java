package cn.com.hik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class LampWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(LampWebApplication.class, args);
    }

}
