<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/27
  Time: 14:41
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
    <c:forEach items="${requestScope.tasks}" var="task">
        ${task.name }&nbsp;<a href="<%=path %>/process/goods_apply_page">做任务</a>
    </c:forEach>
</body>
</html>
