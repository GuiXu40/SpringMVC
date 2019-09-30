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
#### :herb:<a href="OneToOneTest"></a>

-----------------------------
#### :herb:一对多
#### :herb:多对多
<p id="p2"></p>

## :ear_of_rice:动态SQL
<a href="#title">:palm_tree:返回目录</a><br>
#### :herb:if
#### :herb:choose
#### :herb:where
#### :herb:set
#### :herb:foreach
#### :herb:bind
