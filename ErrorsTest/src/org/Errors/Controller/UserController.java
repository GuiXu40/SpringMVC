package org.Errors.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.Errors.entity.User;
import org.Errors.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @RequestMapping(value = "/registerForm",method = RequestMethod.GET)
    public String registerForm(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "registerForm";
    }

    @InitBinder
    public void initBinder(DataBinder binder){
        //设置验证的类为UserValidator
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Validated User user, Errors errors, Model model, @RequestParam("username") String username,@RequestParam("sex") String sex,@RequestParam("age") Integer age){
        //如果Errors对象有Field错误时,重新跳回注册页面,否则正常提交
        if (errors.hasFieldErrors()){
            return "registerForm";
        }else{
            User user1=new User(username,sex,age);
            model.addAttribute("user1",user1);
            return "submit";
        }

    }
}
