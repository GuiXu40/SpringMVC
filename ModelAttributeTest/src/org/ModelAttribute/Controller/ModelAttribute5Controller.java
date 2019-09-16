package org.ModelAttribute.Controller;

import org.ModelAttribute.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute5Controller {
    @ModelAttribute("user")
    public User userModel5(@RequestParam("loginname") String loginname,@RequestParam("password") String password){
        User user=new User(loginname,password);
        return user;
    }
//@ModelAttribute("user") User user注释方法参数,参数user的值就是userModel5方法中的model值
    @RequestMapping(value = "/login5")
    public String login5(@ModelAttribute("user") User user){
        user.setUsername("管理员");
        return "result5";
    }
}
