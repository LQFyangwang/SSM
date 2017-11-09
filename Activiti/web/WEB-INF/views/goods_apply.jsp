<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/27
  Time: 14:34
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
    <form action="<%=path %>/process/submit" method="post">
        <input type="hidden" name="taskId" value="${requestScope.taskId}" />
        <input name="goodsName" />
        <input name="quantity" />
        <input name="reason" />
        <input type="submit" />
    </form>
</body>
</html>
