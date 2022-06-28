package com.xiatu.mvc.controller;

import com.xiatu.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class ParamController {

    @RequestMapping("/param")
    public String param(){
        return "param_test";
    }

    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }

    @RequestMapping("/testParam")
    public String testParam(@RequestParam("user_name") String username, String password, String[] hobby){
        System.out.println("username:"+username+",password:"+password+",hobby:"+ Arrays.toString(hobby));
        return "success";
    }

    @RequestMapping("/testpojo")
    public String testpojo(User user){
        System.out.println(user);
        return "success";
    }



}
