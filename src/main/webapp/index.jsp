<%--
  Created by IntelliJ IDEA.
  User: fd
  Date: 2018/7/16
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="login.action">
    username: <input type="text" name="username" title="用户名"/>
    password: <input type="password" name="password" title="密码"/>
    <button type="submit">login</button>
</form>
<form method="post" action="register.action">
    username: <input type="text" name="username" title="用户名"/>
    password: <input type="password" name="password" title="密码"/>
    <button type="submit">register</button>
</form>
</body>
</html>
