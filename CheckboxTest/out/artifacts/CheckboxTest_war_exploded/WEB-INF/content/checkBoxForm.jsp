<%--
  Created by IntelliJ IDEA.
  User: 桂旭
  Date: 2019/9/18
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>checkBox</title>
</head>
<body>
<form:form method="post" action="register" modelAttribute="user">
    <table>
        <tr>
            <td>选择课表:</td>
            <td>
                <form:checkbox path="coursees" value="JAVAEE" label="JAVAEE"/>&nbsp;
                <form:checkbox path="coursees" value="Mybatis" label="Mybatis"/>&nbsp;
                <form:checkbox path="coursees" value="Spring" label="Spring"/>&nbsp;
            </td>
        </tr>
    </table>
    <form:checkbox path="reader" value="true"/>已经阅读相关协议
</form:form>
</body>
</html>
