<%--
  Created by IntelliJ IDEA.
  User: 桂旭
  Date: 2019/9/19
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="user" method="post" action="register">
    <table>
        <tr>
            <td>姓名:</td>
            <td><form:input path="username"/></td>
            <td><span style="color: red; "><form:errors path="username"/></span></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td><form:input path="sex" /></td>
            <td><span style="color: red; "><form:errors path="sex"/></span></td>
        </tr>
        <tr>
            <td>年龄:</td>
            <td><form:input path="age" /></td>
            <td><span style="color: red; "><form:errors path="age"/></span></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
