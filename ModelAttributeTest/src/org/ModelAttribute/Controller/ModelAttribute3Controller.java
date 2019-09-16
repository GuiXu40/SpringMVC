package org.ModelAttribute.Controller;

import org.ModelAttribute.entity.User;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ModelAttribute3Controller {
    private List<User> userList;
    public ModelAttribute3Controller(){
        super();
        userList=new ArrayList<User>();
        User user1=new User("text","123456","测试用户");
        User user2=new User("admin","123456","管理员");
        userList.add(user1);
        userList.add(user2);
    }

    public User find(String loginname,String password){
        for (User user:userList){
            if (user.getLoginname().equals(loginname) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    //model属性的名称没有被指定,他由返回类型隐含表示,如这个方法返回User类型,那么这个model属性的名称是user
    @ModelAttribute
    public User userModel3(@RequestParam("loginname") String loginname,@RequestParam("password") String password){
        return find(loginname,password);
    }

    @RequestMapping(value = "/login3")
    public String login3(){
        return "result3";
    }
}
