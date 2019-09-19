<%--
  Created by IntelliJ IDEA.
  User: 桂旭
  Date: 2019/9/19
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="register" modelAttribute="employee">
    <table>
        <tr>
            <td>选择课表:</td>
            <td>
                <form:checkboxes items="${deptList}" path="depts" itemLabel="name" itemValue="id"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
