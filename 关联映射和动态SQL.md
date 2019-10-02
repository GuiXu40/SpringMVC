# ------------关联映射和动态SQL---------------
<p id="title"></p>

## 目录
<a href="#p1">:chestnut:关联映射</a><br>
<a href="#p2">:chestnut:动态SQL</a><br>

<p id="p1"></p>

## :ear_of_rice:关联映射
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:一对一
实例: 一个人只有一个身份证

创建两个表tb_card和tb_person
```Mysql
CREATE TABLE tb_card(
		id INT PRIMARY KEY AUTO_INCREMENT,
		CODE VARCHAR(18)
);
INSERT INTO TB_card(CODE) VALUES('500234200106166033');
CREATE TABLE tb_person(
		id INT PRIMARY KEY AUTO_INCREMENT,
		NAME VARCHAR(18),
		sex VARCHAR(18),
		age INT,
		card_id INT UNIQUE,
		FOREIGN KEY (card_id) REFERENCES tb_card (id)
);
INSERT INTO tb_person(name,sex,age,card_id) VALUES('guixu','男',18,1);
```
创建Card对象和Person对象分别映射两个表
```Java
package org.Mybatis.domain;
public class Card {
    private Integer id;
    private String code;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return "id: "+id+"--code: "+code;
    }
}
```
Person
```Java
package org.Mybatis.domain;
public class Person {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Card card;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    @Override
    public String toString() {
        return "id: "+id+"--name: "+name+"--sex: "+sex+"--age: "+age+"--card: "+card;
    }
}
```
**XML文件**

CardMapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.CardMapper">
<!--    根据id查询Card,返回Card对象-->
    <select id="selectCardById" parameterType="int" resultType="org.Mybatis.domain.Card">
        SELECT * from tb_card where id=#{id}
    </select>
</mapper>
```
PersonMapepr.xml
```xml 
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.PersonMapper">
    <!--    根据id查询person,返回resultMap-->
    <select id="selectPersonById" parameterType="int" resultMap="personMapper">
        SELECT * from tb_person where id=#{id}
    </select>
<!--    映射Person对象的resultMap对象-->
    <resultMap id="personMapper" type="org.Mybatis.domain.Person">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="sex" column="sex"/>
        <result property="age" column="age"/>
<!--        一对一关联映射-->
        <association property="card" column="card_id" select="org.Mybatis.mapper.CardMapper.selectCardById" javaType="org.Mybatis.domain.Card"/>
    </resultMap>
</mapper>
```
**通常使用Mapper接口的代理对象访问Mybatis-->Mapper接口对象的类名必须和之前的xml文件中的mapper的namespace一致,方法名和参数也必须对应**
```Java
package org.Mybatis.mapper;

import org.Mybatis.domain.Person;

public interface PersonMapper {
//    根据id查询person
//    方法名和参数必须和xml文件中的<select..>元素的id属性和parameterType属性一致
    Person selectPersonById(Integer id);
}
```
最后是OneToOneTestTest.java
```java 
package org.Mybatis.test;

import org.Mybatis.domain.Person;
import org.Mybatis.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class OneToOneTest {
    public static void main(String[] args) throws IOException {
        //读取mybatis-config.xml配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //创建session实例
        SqlSession session=sqlSessionFactory.openSession();
        //获得mapper接口的代理对象
        PersonMapper pm=session.getMapper(PersonMapper.class);
        //直接调用接口的方法,查询id为1的Person数据
        Person p=pm.selectPersonById(1);
        //打印person对象
        System.out.println(p);
        //打印Person关联的Card对象
        System.out.println(p.getCard());
        //提交事务
        session.commit();
        //关闭session
        session.close();
    }
}
```
其他配置文件和相关目录结构查看完整项目:

-----------------------------
#### :herb:<a href="OneToOneTest">OneToOneTest</a>
-----------------------------
#### :herb:一对多
例子: 一个班级对应多个学生,多个学生对应一个学生

**第一步:** 编写数据库
```Mysql
CREATE TABLE tb_clazz(
		id INT PRIMARY KEY AUTO_INCREMENT,
		CODE VARCHAR(18),
		NAME VARCHAR(18)
);
Insert into tb_clazz(CODE,name) VALUES('2018','计算机专业班');

CREATE TABLE tb_student(
		id INT PRIMARY KEY AUTO_INCREMENT,
		NAME VARCHAR(18),
		sex varchar(18),
		age int,
		clazz_id INT,
		FOREIGN KEY (clazz_id) REFERENCES tb_clazz(id)
);
INSERT INTO tb_student(name,sex,age,clazz_id) VALUES('guixu','男',18,1);
INSERT INTO tb_student(name,sex,age,clazz_id) VALUES('ysl','女',19,1);
INSERT INTO tb_student(name,sex,age,clazz_id) VALUES('tom','男',21,1);
INSERT INTO tb_student(name,sex,age,clazz_id) VALUES('alice','女',20,1);
```
**第二步**: 创建对应表的实体类:

Student
```Java
package org.Mybatis.domain;
public class Student {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Clazz clazz;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Clazz getClazz() {
        return clazz;
    }
    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
    @Override
    public String toString() {
        return "id: "+id+"-->name: "+name+"-->sex: "+sex+"-->age: "+age;
    }
}
```
clazz
```Java
package org.Mybatis.domain;
import java.util.List;
public class Clazz {
    private Integer id;
    private String code;
    private String name;
    private List<Student> students;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    @Override
    public String toString() {
        return "id:"+id+"-->code: "+code+"-->name:"+name;
    }
}
```
**第三步**: 编写xml配置文件

ClazzMapper:
```Java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.ClazzMapper">
<!--    根据id查询班级信息,返回resultMap-->
    <select id="selectClazzById" parameterType="int" resultMap="clazzResultMap">
        select * from tb_clazz where id=#{id};
    </select>
<!--    映射Clazz对象的resultMap-->
    <resultMap id="clazzResultMap" type="org.Mybatis.domain.Clazz">
        <id property="id" column="id"/>
        <result property="code" column="code"/>
        <result property="name" column="name"/>
<!--        一对多关联映射:collection fetchType="lazy"表示懒加载-->
        <collection property="students" column="id" javaType="ArrayList" ofType="org.Mybatis.domain.Student" select="org.Mybatis.mapper.StudentMapper.selectStudentByClazzId" fetchType="lazy">
            <id property="id" column="id"/>
            <result property="name" column="name"/>
            <result property="sex" column="sex"/>
            <result property="age" column="age"/>
        </collection>
    </resultMap>
</mapper>
```
StudentMapper
```Java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.StudentMapper">
<!--    根据id查询学生信息,多表连接,返回resultMap-->
    <select id="selectStudentById" parameterType="int" resultMap="studentResultMap">
        select * from tb_clazz c,tb_student s
        where c.id=s.clazz_id
        and s.id=#{id}
    </select>
<!--    根据班级id查询学生信息,返回resultMap-->
    <select id="selectStudentByClazzId" parameterType="int" resultMap="studentResultMap">
        select * from tb_student where clazz_id=#{id}
    </select>
<!--    映射Student对象的resultMap-->
    <resultMap id="studentResultMap" type="org.Mybatis.domain.Student">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="sex" column="sex"/>
        <result property="age" column="age"/>
<!--        多对一关联映射: association-->
        <association property="clazz" javaType="org.Mybatis.domain.Clazz">
            <id property="id" column="id"/>
            <result property="code" column="code"/>
            <result property="name" column="name"/>
        </association>
    </resultMap>
</mapper>
```
collocation-->用来映射一对多的关系

select: 会使用column属性的id值作为参数执行StudentMapper中定义的selectStudentByClazzID查询改班级的学生数据

查询出来的数据会被封装到property表示的students对象中

fetchType: (eager/lazy),eager表示立刻加载,lazy表示懒加载,不会立刻发送SQL语句去查询班级的所有学生

此属性 需要在主配置文件中配置
```Java
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
```
lazyLoadingEnabled: 表示延迟加载的全局开关,所有关联对象都会延迟加载,默认为 false

aggressiveLazyLoading: 使带有延迟属性的对象立即加载(应被设置为false)

**第四步**: Mapper接口对象

ClazzMapper.java
```java
import org.Mybatis.domain.Clazz;
public interface ClazzMapper {
    Clazz selectClazzById(Integer id);
}
```
StudentMapper.java
```java
import org.Mybatis.domain.Student;

public interface StudentMapper {
    Student selectStudentById(Integer id);
}
```
**第五步**: OneToManyTest
```Java
package org.Mybatis.test;

import org.Mybatis.domain.Clazz;
import org.Mybatis.domain.Student;
import org.Mybatis.mapper.ClazzMapper;
import org.Mybatis.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OneToManyTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        OneToManyTest t=new OneToManyTest();
        //测试一对多
        t.testSelectClazzById(session);
        t.testSelectStudentById(session);
        //提交事务
        session.commit();
        //关闭
        session.close();
    }

    public void testSelectClazzById(SqlSession session){
        ClazzMapper cm=session.getMapper(ClazzMapper.class);
        //调用selectClazzById方法
        Clazz clazz=cm.selectClazzById(1);
        //查看查询到的clazz对象信息
        System.out.println(clazz.getId()+" "+clazz.getCode()+" "+clazz.getName());
        //查看clazz对象关联的学生信息
        List<Student> students=clazz.getStudents();
        for (Student student:students){
            System.out.println(student);
        }
    }
    public void testSelectStudentById(SqlSession session){
        //获得StudentMapper接口的代理对象
        StudentMapper sm=session.getMapper(StudentMapper.class);
        Student student=sm.selectStudentById(1);
        System.out.println(student);
        System.out.println(student.getClazz());
    }
}
```
#### :herb:多对多
例: 一个购物系统,一个用户可以有多个订单,一个订单有多种商品,商品和订单就是多对多的关系

对于数据库中多对多关系建议使用一个中年表来维护关系

**第一步**: 创建数据库中的表
```mysql
#创建用户表
create table tb_user(
	id int primary key auto_increment,
	username varchar(18),
	loginname varchar(18),
	password varchar(18),
	phone varchar(18),
	address varchar(18)
);
# 插入用户表测试数据
Insert into tb_user(username,loginname,password,password,phone,address) values('桂旭','guixu','123456','18428022456','成都');
#创建商品表
create table tb_article(
	id int primary key auto_increment,
	name varchar(18),
	price double,
	remark varchar(18)
);
#插入商品测试数据
insert into tb_article(name,price,remark) values('疯狂Java讲义',152.5,'李刚老师经典著作');
insert into tb_article(name,price,remark) values('疯狂Android讲义',142.5,'李刚老师经典著作');
insert into tb_article(name,price,remark) values('疯狂iOS讲义',155.5,'李刚老师经典著作');
#创建订单表
create table tb_ordr(
	id int primary key auto_increment,
	code varchar(18),
	total double,
	user_id Int,
	foreing key (user_id) references tb_user(id)
);
#插入订单数据
insert into tb_order(code,total,user_id) values('123465',45.1,1);
insert into tb_order(code,total,user_id) values('555465',45.1,1);
#创建中间表
create table tb_item(
	order_id int,
	article_id int,
	amount int,
	Primary key(order_id,article_id),
	foreing key (order_id) refereces tb_order(id),
	foreing key (article_id) refereces tb_article(id)
);
#创建插入中间表数据
insert into tb_item(order_id,article_id ,amount) values(1,1,1);
insert into tb_item(order_id,article_id ,amount) values(1,2,1);
insert into tb_item(order_id,article_id ,amount) values(1,3,2);
insert into tb_item(order_id,article_id ,amount) values(2,4,1);
insert into tb_item(order_id,article_id ,amount) values(2,1,1);
```

**第2步**: 创建映射数据表的对象

User
```Java
package org.Mybatis.domain;

import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String loginname;
    private String password;
    private String phone;
    private String address;
    //用户和订单是一对多关系,一个用户-->多个订单
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "id: "+id+"-->username: "+username+"-->loginname: "+loginname+"-->phone: "+phone+"-->address:"+address;
    }
}
```
order
```Java
package org.Mybatis.domain;

import java.util.List;

public class Order {
    private Integer id;
    private String code;
    private Double total;
    //订单和用户是多对一,一个订单-->一个用户
    private User user;
    //订单和商品是多对多关系
    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "id: "+id+"-->code: "+code+"-->total: "+total;
    }
}
```
article
```Java
package org.Mybatis.domain;

import java.util.List;

public class Article {
    private Integer id;
    private String name;
    private Double price;
    private String remark;
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "id:"+id+"-->name: "+name+"-->price: "+ price+"-->remark: "+remark+"-->order: "+orders;
    }
}
```
**第3步**: xml文件

UserMapper
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.UserMapper">
    <resultMap id="userResultMap" type="org.Mybatis.domain.User">
        <id property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="loginname" column="loginname"/>
        <result property="password" column="password"/>
        <result property="phone" column="phone"/>
        <result property="address" column="address"/>
        <collection property="orders" javaType="ArrayList" column="id" ofType="org.Mybatis.domain.User" select="org.Mybatis.mapper.OrderMapper.selectOrderByUserId" fetchType="lazy">
            <id property="id" column="id"/>
            <result property="code" column="code"/>
            <result property="total" column="total"/>
        </collection>
    </resultMap>
        <select id="selectUserById" resultMap="userResultMap" parameterType="int">
            select * from tb_user where id=#{id}
        </select>
</mapper>
```
OrderMapper
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.OrderMapper">
    <resultMap id="orderResultMap" type="org.Mybatis.domain.Order">
        <id property="id" column="id"/>
        <result property="code" column="code"/>
        <result property="total" column="total"/>
        <association property="user" javaType="org.Mybatis.domain.User">
            <id property="id" column="id"/>
            <result property="username" column="username"/>
            <result property="loginname" column="loginname"/>
            <result property="password" column="password"/>
            <result property="phone" column="phone"/>
            <result property="address" column="address"/>
        </association>
        <collection property="articles" javaType="ArrayList" column="oid" ofType="org.Mybatis.domain.Article" select="org.Mybatis.mapper.ArticleMapper.selectArticleByOrderId" fetchType="lazy">
            <id property="id" column="id"/>
            <result property="name" column="name"/>
            <result property="price" column="price"/>
            <result property="remark" column="remark"/>
        </collection>
    </resultMap>
    <select id="selectOrderById" parameterType="int" resultMap="orderResultMap">
        select u.*,o.id As oid,code,total,user_id from tb_user u,tb_order o where u.id=o.user_id and o.id=#{id};
    </select>
<!--    根据user_id查询订单-->
    <select id="selectOrderByUserId" parameterType="int" resultType="org.Mybatis.domain.Order">
        select * from tb_order where user_id=#{id}
    </select>
</mapper>
```
article
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.Mybatis.mapper.ArticleMapper">
    <select id="selectArticleByOrderId" parameterType="int" resultType="org.Mybatis.domain.Article">
        select * from tb_article where id in (select article_id from tb_item where order_id =#{id})
    </select>
</mapper>
```

**第4步**: mapper接口对象

UserMapper
```java 
package org.Mybatis.mapper;

import org.Mybatis.domain.User;

public interface UserMapper {
    User selectUserById(int id);
}
```
OrderMapper
```Java
package org.Mybatis.mapper;

import org.Mybatis.domain.Order;

public interface OrderMapper {
    Order selectOrderById(int id);
}
```
**第5步**: test
```Java
package org.Mybatis.test;

import org.Mybatis.domain.Article;
import org.Mybatis.domain.Order;
import org.Mybatis.domain.User;
import org.Mybatis.mapper.OrderMapper;
import org.Mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ManyToManyTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        //创建session实例
        SqlSession session=sqlSessionFactory.openSession();
        ManyToManyTest t=new ManyToManyTest();
        t.testSelectUserById(session);
        t.testSelectOrderById(session);
        session.commit();
        session.close();
    }
    //测试一对多关系,查询User(一)-->Order(多)
    public void testSelectUserById(SqlSession session){
        //获得UserMapper代理对象
        UserMapper um=session.getMapper(UserMapper.class);
        //调用selectUserById方法
        User user=um.selectUserById(1);
        //查看查询到的user对象信息
        System.out.println(user.getId()+" "+user.getUsername());
        //查看user对象关联的订单信息
        List<Order> orders=user.getOrders();
        for (Order order: orders){
            System.out.println(order);
        }
    }
    //测试多对多关系,查询Order(多)-->Article(多)
    public void testSelectOrderById(SqlSession session){
        //获取OrderMapper对象
        OrderMapper om=session.getMapper(OrderMapper.class);
        //调用方法
        Order order =om.selectOrderById(2);
        //查看查询到的order对象信息
        System.out.println(order.getId()+" "+order.getCode()+" "+order.getTotal());
        //查看order对象关联的用户信息
        User user=order.getUser();
        System.out.println(user);
        //查看Order 对象关联的商品信息
        List<Article> articles=order.getArticles();
        for (Article article:articles){
            System.out.println(article);
        }
    }
}
```

----------------------------
#### :herb:<a href="ManyToMany">多对多</a>,其中用到了数据库的中间表查询(目前不理解)

----------------------------
<p id="p2"></p>

## :ear_of_rice:动态SQL
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:if
实例:

创建表
```mysql
create table tb_employee(
		id int(11) primary key auto_increment,
		loginname varchar(18),
		password varchar(18),
		name varchar(18) default null,
		sex char(2) default null,
		age int(11) default null,
		phone varchar(21), 
		sal double,
		state varchar(18)
);
insert into tb_employee(loginname,password,name,sex,age,phone,sal,state) values('guixu','123456','桂旭','男',18,'18428042456',1200,'active');
insert into tb_employee(loginname,password,name,sex,age,phone,sal,state) values('xiaoming','123123','小明','男',22,'18428445545',4200,'active');
insert into tb_employee(loginname,password,name,sex,age,phone,sal,state) values('xiaohong','123123','小红','女',19,'18428042456',1500,'active');
insert into tb_employee(loginname,password,name,sex,age,phone,sal,state) values('gaoju','123521','高巨','男',28,'18428042456',7200,'active');
```
#### :herb:choose
#### :herb:where
#### :herb:set
#### :herb:foreach
#### :herb:bind
