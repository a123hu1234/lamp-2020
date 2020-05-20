package cn.com.hik.lamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/16 17:41
 */
@Controller
@RequestMapping("/home")
public class HomeConroller {

    @PostMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        return "hello";
    }
}
