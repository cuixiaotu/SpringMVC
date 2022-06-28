package com.xiatu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/test")
public class HomeController {

    //打开类上的RequestMapping注解时映射 增加一层路径test
    @RequestMapping("/")
    public String index(){
        //返回视图名称
        return "index";
    }

    @RequestMapping(
            value = {"testRequestMapping","test"}
    )
    public String success(){
        return "success";
    }

    @RequestMapping(
            value = {"testRequestMapping2","test2"},
            method={RequestMethod.GET,RequestMethod.POST}
    )
    public String success2(){
        return "success";
    }

    @RequestMapping(
            value = {"testRequestMapping3","test3"},
            method={RequestMethod.GET,RequestMethod.POST},
            params = {"username=xiaotu","password!=123456"}
    )
    public String success3(){
        return "success";
    }

    @RequestMapping(
            value = {"testRequestMapping4","test4"},
            headers = { "Connection:keep-alive"}
    )
    public String success4(){
        return "success";
    }
    @RequestMapping(
            value = {"test*5"}
    )
    public String success5(){
        return "success";
    }

    //RestFul风格
    @RequestMapping("/testRestFul/{id}/{username}")
    public String testRest(@PathVariable("id") String id, @PathVariable("username") String username){
        System.out.println("id:"+id+",username:"+username);
        return "success";
    }

}
