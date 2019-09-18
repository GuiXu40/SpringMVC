package org.Form.Controller;

import org.Form.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
//    @RequestMapping(value = "/registerForm",method = RequestMethod.GET)
//    public String registerForm(Model model){
//        User user=new User("guixu","男",18);
//        model.addAttribute("command",user);
//        return  "registerForm";
//    }
    @RequestMapping(value = "/registerForm2",method = RequestMethod.GET)
    public String registerForm(Model model){
        User user=new User("guixu","男",18);
        model.addAttribute("user",user);
        return  "registerForm2";
    }
}
