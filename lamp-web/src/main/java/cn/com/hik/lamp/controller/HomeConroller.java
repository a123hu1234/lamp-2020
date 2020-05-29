package cn.com.hik.lamp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("home")
public class HomeConroller {

    @PostMapping("/hello")
    @ResponseBody
    @ApiOperation("hello")
    public String helloWorld(){
        return "hello";
    }
}
