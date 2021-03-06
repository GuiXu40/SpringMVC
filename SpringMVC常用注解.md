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
+ HttpServletRequest
+ HttpSession
#### :herb:请求处理方法可返回的类型
#### :herb:Model和ModelMap的使用
<p id="p3"></p>

## :ear_of_rice:参数绑定注解
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:@Requestparam注解
用于将指定的请求参数赋值给方法中的形参

属性|说明
---|:--:
name|指定请求头绑定的名称
value|name属性的别名
required|指示参数是否必须绑定
defaultValue|如果没有参数而使用默认的参数
```Java
@requestMapping(value="/login")
public ModelAndView login(@requestParam("loginname") String loginname,@requesparam("password") String password){
    return...
}
```
其他写法
```java
@RequestParam(value="loginname",required=true,defaultValue="admire")
```
required参不是必须的,默认为true
#### :herb:@Path Variable注解
可以很方便的获取请求URL中的动态参数,只有一个属性value,类型为String
```Java
@RequestMapping(value="/pathVariableTest/{userId}")
public void pathVariableTest(@PathVariable Interger userId)
```


#### :herb:@RequestHeader注解
用于将请求头的头信息区数据映射到功能处理的方法的参数上

属性|说明
---|:--:
name|指定请求头绑定的名称
value|name属性的别名
required|指示参数是否必须绑定
defaultValue|如果没有参数而使用默认的参数

```Java
@RequestMapping(value="/requestHeaderTest")
    public void requestHeaderTest(@RequestHeader("User-Agent") String userAgent,@RequesetHeader(value="Accept") String[] accepts)
```
#### :herb:@CookieValue注解
用于将请求的Cookie数据映射到功能处理方法的参数上

#### :herb:@SessionAttributes注解
#### :herb:@ModelAttribute注解
将请求参数绑定到Model对象-->只有一个value属性
<br>**被@ModelAttribute注释的方法会在Controller每个方法前被执行,因此,在一个Controller映射到多个URL时,要谨慎使用**
<br>
有5中使用方式
+ @ModelAttribute(value="")注释返回具体类的方法
+ @ModelAttribute注释void返回值的方法
+ @ModelAttribute注释返回具体类的方法
+ @ModelAttribute和@RequestMapping同时注释一个方法
+ @ModelAttribute注释一个方法的参数

参考实例:<a href="ModelAttributeTest">ModelAttributeTest</a>
<p id="p4"></p>

## :ear_of_rice:信息转换
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:HttpMessageConverter接口
#### :herb:装换JSON数据
#### :herb:转换XML数据
