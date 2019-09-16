# -------------SpringMVC常用注解----------------
<p id="title"></p>

## 目录
<a href="#p1">:chestnut:@Controller注解</a><br>
<a href="#p2">:chestnut:@RequestMapping注解</a><br>
<a href="#p3">:chestnut:参数绑定注解</a><br>
<a href="#p4">:chestnut:信息转换</a><br>

<p id="p1"></p>

## :ear_of_rice:@Controller注解
<a href="#title">:palm_tree:返回目录</a><br>
用于指示Spring类的实例是一个控制器,不需要继承特定的父类或者实现特定的接口
**实例**:
```Java
package org.guixu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    @RequestMapping("helloWorld")
    public String helloWorld(Model model){
        model.addAttribute("message","Hello world");
        return  "helloWorld";
    }
}
```
在配置文件中也要改变
```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
        <context:component-scan base-package="org.guixu.controller"/>
<!--        视图解析器-->
        <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
<!--            前缀-->
            <property name="prefix">
                <value>/WEB-INF/content/</value>
            </property>
<!--            后缀-->
            <property name="suffix">
                <value>.jsp</value>
            </property>
        </bean>
</beans>
```
<p id="p2"></p>

## :ear_of_rice:@RequestMapping注解
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:@RequestMapping注解
用来注解一个控制器类,所有方法都将映射为相对于类级别的请求,表示改代码处理的所有请求都被映射到 value属性所指示的路径下

支持的属性

属性|类型|说明
---|:--:|:--:
value|String[]|用于将指定请求的实际地址映射到方法上
name|String|给映射地址指定一个别名
comsumes|String[]|指定处理请求的方法类型(Content-Type)
method|RequestMethod[]|映射指定请求的方法类型
produces|String[]|指定返回的内容类型,返回的类型必须是request请求头(Accept)中包含的类型
params|String]|指定request中包含某些参数时,才使用该方法
headers|String[]|指定request中必须包含某些指定的header
Path|String[]|在Servlet环境中只有:url路径映射

#### :herb:请求处理方法可出现的参数类型
#### :herb:请求处理方法可返回的类型
#### :herb:Model和ModelMap的使用
<p id="p3"></p>

## :ear_of_rice:参数绑定注解
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:@Requestparam注解
#### :herb:@Path Variable注解
#### :herb:@RequestHeader注解
#### :herb:@CookieValue注解
#### :herb:@SessionAttributes注解
#### :herb:@ModelAttribute注解
<p id="p4"></p>

## :ear_of_rice:信息转换
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:HttpMessageConverter接口
#### :herb:装换JSON数据
#### :herb:转换XML数据
