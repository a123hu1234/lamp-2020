package cn.com.hik.lamp.config.maybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/26 10:55
 */
@Configuration
@MapperScan("cn.com.hik.lamp.common.mapper")
public class MaybatisConfig {
}
