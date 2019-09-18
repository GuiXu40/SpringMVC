package org.CheckBox.Controller;

import org.CheckBox.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping(value = "/checkBoxForm",method = RequestMethod.GET)
    public String registerForm(Model model){
        List<String> list=new ArrayList<>();
        list.add("JAVAEE");
        list.add("Spring");
        User user=new User(list);
        model.addAttribute("user",user);
        return "checkBoxForm";
    }
}
