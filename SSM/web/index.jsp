<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/9
  Time: 10:19
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
  <form action="<%=path %>/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" />
  </form>

  </body>
</html>
