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
    <form id="userLogin">
        <input name="name" />
        <input type="password" name="password" />
        <input type="button" onclick="login();" value="登录"/>
    </form>
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script>

    function login() {
        $.post("<%=path%>/user/login1",
            $("#userLogin").serialize(),
            function (data) {
                alert(data.msg);
            }, "json"
        );
    }

</script>
</body>
</html>
