<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/27
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${requestScope.tasks}" var="task">
    ${task.name }&nbsp;<a href="<%=path %>/process/check/${task.id}">审核</a>
</c:forEach>
</body>
</html>
