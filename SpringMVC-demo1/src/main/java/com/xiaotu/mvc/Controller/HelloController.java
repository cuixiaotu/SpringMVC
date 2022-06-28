package com.xiaotu.mvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller /*自动扫描在配置文件内*/
public class HelloController {

    @RequestMapping("/")
    public String index(){
        //返回视图名称
        return "index";
    }


    @GetMapping("/target")
    public String toTarget(){
        return "target";
    }
}
