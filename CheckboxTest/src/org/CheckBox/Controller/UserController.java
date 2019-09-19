package org.CheckBox.Controller;

import org.CheckBox.entity.Dept;
import org.CheckBox.entity.Employee;
import org.CheckBox.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping(value = "/checkBoxesForm",method = RequestMethod.GET)
    public String registerForm(Model model){
//        List<String> list=new ArrayList<>();
//        list.add("JAVAEE");
//        list.add("Spring");
//        User user=new User(list);
//        //页面展示的可供选择的复选框内容courseList
//        List<String> courseList=new ArrayList<>();
//        courseList.add("JAVVEE");
//        courseList.add("Mybatis");
//        courseList.add("Spring");
//        model.addAttribute("user",user);
//        model.addAttribute("courseList",courseList);
//        return "checkBoxesForm";
        ///////////////////////////////////////////////////////////////////绑定Map数据
//        list.add("1");
//        list.add("3");
//        User user=new User(list);
//        //页面展示的可供选择的复选框内容courseMap
//        Map<String,String> courseMap=new HashMap<>();
//        courseMap.put("1","JAVAEE");
//        courseMap.put("2","mybatis");
//        courseMap.put("3","Spring");
//        model.addAttribute("user",user);
//        model.addAttribute("courseMap",courseMap);
//        return "checkBoxesForm2";
        ////////////////////////////////////////////////////////////////////绑定数组数据
        Employee employee=new Employee();
        List<Dept> list=new ArrayList<>();
        list.add(new Dept(1,"开发部"));
        employee.setDepts(list);
        List<Dept> deptList=new ArrayList<>();
        deptList.add(new Dept(2,"销售部"));
        deptList.add(new Dept(3,"财务部"));
        model.addAttribute("employee",employee);
        model.addAttribute("deptList",deptList);
        return "checkBoxesForm3";
    }
}
