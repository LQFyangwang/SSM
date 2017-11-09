<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/27
  Time: 14:08
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
<table>
    <th><td>流程名</td><td>路径</td><td>操作</td></th>
    <c:forEach items="${requestScope.processes}" var="process">
        <tr>
            <td>${process.name}</td>
            <td>${process.path}</td>
            <td><a href="<%=path %>/process/deploy/${process.name}">部署流程</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
