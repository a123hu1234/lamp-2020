package cn.com.hik.lamp.lampcommon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

//@SpringBootTest
class LampCommonApplicationTests {

    public static void main(String[] args) {
        Properties properties =  System.getProperties();
        properties.stringPropertyNames().forEach(name -> System.out.println(name +"="+properties.getProperty(name)));
    }

    @Test
    void contextLoads() {
        Properties properties =  System.getProperties();
        properties.stringPropertyNames().forEach(name -> System.out.println(name +"="+properties.getProperty(name)));
    }

}
