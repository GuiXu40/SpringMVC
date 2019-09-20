package org.Converter.Controller;

import org.Converter.converter.DateEditor;
import org.Converter.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class UserController {
      private static final Log logger= (Log) LogFactory.getLog(UserController.class);
    @RequestMapping(value = "/{formName}")
    public String loginForm(@PathVariable String formName){
        return formName;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model){
        logger.info(user);
        model.addAttribute("user",user);
        return "success";
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        //注册自定义编辑器
//        binder.registerCustomEditor(Date.class,new DateEditor());
//    }
}
