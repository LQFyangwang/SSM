<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/18
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="<%=path %>/user/login" method="post">
    <input name="name" />
    <input type="password" name="password" />
    <input type="submit" />
  </form>
  <shiro:authenticated>
    需要认证才可以看的内容
  </shiro:authenticated>
  <shiro:hasRole name="customer">
    指定角色才可以看的内容
  </shiro:hasRole>
  <shiro:hasPermission name="customer:update">
    拥有指定权限的的可以看的内容
  </shiro:hasPermission>

    不需要认证的可以查看的内容
  </body>
</html>
