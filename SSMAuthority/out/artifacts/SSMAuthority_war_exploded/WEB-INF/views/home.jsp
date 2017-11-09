<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/17
  Time: 9:05
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
    ${sessionScope.user.name}<br />
    ${sessionScope.user.role.name }
    <br />
    <a href="<%=path %>/user/hire">招聘员工</a>
    <a href="<%=path %>/user/fire">解聘员工</a>
</body>
</html>
