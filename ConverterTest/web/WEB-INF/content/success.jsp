<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: 桂旭
  Date: 2019/9/19
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
登录名: ${requestScope.user.loginname}<br>
生日:  ${requestScope.user.birthday}<br>
</body>
</html>
