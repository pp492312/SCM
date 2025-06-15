package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;





@Controller
public class PageControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

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
    
    // contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // login page
    // this is showing login page

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    
    // Registration or signup page

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm=new UserForm();
        // default data
        //userForm.setName("Pramod");
        model.addAttribute("userForm", userForm);

        return "register";
    }
    
    // processing register

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session){
        //System.out.println("Process hoo raha hai");
        
        // fecth from data
        // User Form
        System.out.println(userForm);

        // validate from data
        if(rBindingResult.hasErrors()){
            return "register";
        }

        // Save to database

        // userservice 

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://stock.adobe.com/in/search/images?k=default+profile+picture&asset_id=875459719")
        // .build();

        User user =new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://stock.adobe.com/in/search/images?k=default+profile+picture&asset_id=875459719");

        User savedUser = userService.saveUser(user);

        System.out.println("User Saved");

        // message = Registration successful

        // add the message

        Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message",message);

        // redirected to login page

        return "redirect:/register";
    }
}
