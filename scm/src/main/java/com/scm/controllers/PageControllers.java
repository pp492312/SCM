package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageControllers {

    @RequestMapping("/home")
    public String home(Model model) {
       // model.addAttribute("isLogin",true);
        System.out.println("Home page hander");
        model.addAttribute("name","Substring technologies");
        model.addAttribute("Youtube", "Learn_Code");

        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin",true);
        System.out.println("About page Loading");
        return "about";
    }

    // Services

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services pag Loading");
        return "services";
    }
    
}
