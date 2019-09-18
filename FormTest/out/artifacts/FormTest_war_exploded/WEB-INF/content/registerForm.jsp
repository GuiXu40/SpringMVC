<%--
  Created by IntelliJ IDEA.
  User: 桂旭
  Date: 2019/9/18
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>表单登录</title>
</head>
<body>
    <form:form method="post" action="register">
        <table>
            <tr>
                <td>姓名:</td>
                <td><form:input path="username"/></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td><form:input path="sex"/></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><form:input path="age"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
