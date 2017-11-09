<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/9
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="userLogin" method="post" action="<%=path %>/user/login2">
        <input name="name" />
        <input type="password" name="password" />
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
