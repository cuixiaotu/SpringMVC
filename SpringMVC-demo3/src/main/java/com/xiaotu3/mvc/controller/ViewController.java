package com.xiaotu3.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/testThymeleafView")
    public String testThymeleafView(){
        return "success";
    }

    @RequestMapping("/testForwardView")
    public String testForwardView(){
        return "forward:/testThymeleafView";
    }

    @RequestMapping("/testRedirectView")
    public String testDirectView(){
        return "redirect:/testThymeleafView";
    }

}
