package com.xiaotu3.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    /* 使用视图控制器
        @RequestMapping("/")
        public String index(){
            return "index";
        }
    */

    @RequestMapping("/test_view")
    public String testView(){
        return "test_view";
    }

}
