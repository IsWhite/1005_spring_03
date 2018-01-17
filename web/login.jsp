<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 18/1/16
  Time: 下午3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%--局部数据刷新用ajax
整个页面刷新用form提交--%>

<h1>登录</h1>
<form action="login.action" method="post">
    用户名:<input type="text" name="sname">
    密码:<input type="password" name="spassword">
    <br><br>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
    <br><br>
    <%--<button id="register">注册1</button>--%>

    <a href="register.jsp">注册2</a>
</form>
</body>
</html>
