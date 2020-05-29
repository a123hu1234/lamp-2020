package cn.com.hik.lamp2020.lampweb;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SystemTest {

    public static void main(String[] args) {
      //  System.out.println(System.getProperties());
      //  System.out.println(System.getenv());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456c"));
    }
}
