<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/10/17
  Time: 9:02
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
    <form id="login">
        <input type="text" name="name" />
        <input type="password" name="password" />
        <input type="button" value="登录" onclick="login();" />
    </form>
</body>
<script src="<%=path %>/static/js/jquery.min.js"></script>
<script>

    function login() {
        $.post("<%=path %>/user/login",
            $("#login").serialize(),
            function (data) {
                if (data.result == "ok") {
                    window.location.href = "<%=path %>/user/home";
                } else {
                    alert(data.message);
                }
            }
        );
    }

</script>
</html>
