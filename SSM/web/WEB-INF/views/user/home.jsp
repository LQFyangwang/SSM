<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/10
  Time: 9:17
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

    ${requestScope.user.name }
    ${sessionScope.user1.name }

</body>
</html>
