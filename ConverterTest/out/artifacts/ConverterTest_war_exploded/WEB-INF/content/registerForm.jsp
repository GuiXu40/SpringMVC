<%--
  Created by IntelliJ IDEA.
  User: 桂旭
  Date: 2019/9/19
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<form action="register" method="post">
    <table>
        <tr>
            <td><label>登录名:</label></td>
            <td><input type="text" id="loginname" name="loginname"/></td>
        </tr>
        <tr>
            <td><label>生日: </label></td>
            <td><input type="text" id="birthday" name="birthday"/></td>
        </tr>
        <tr>
            <td><input type="submit" id="submit" value="登录"/></td>
        </tr>
    </table>
</form>
</body>
</html>
