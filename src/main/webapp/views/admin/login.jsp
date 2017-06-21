<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String redirectUrl = request.getParameter("redirectUrl");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <form action="/Codes/c/admin/login?redirectUrl=<%=redirectUrl == null || redirectUrl.isEmpty() ? "" : redirectUrl%>" method="post">
            用户名：<input type="text" name="username" /><br/>
            密码：<input type="password" name="password" /><br/>
            <button type="submit">登录</button>
        </form>
    </body>
</html>
