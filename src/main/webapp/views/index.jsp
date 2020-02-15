<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 13.02.2020
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%
    if (request.getAttribute("login") != null) {
        response.getWriter().println(request.getAttribute("login"));
    }
%>

<form action="/login" method="post">
    <b>
        login
    </b></br>
    <input type="text" name="username" size="40" >
    </br>
    <b>
        password
    </b></br>
    <input type="password" name="password" size="40" >
    </br>
    <input type="checkbox" name="remember" value="true"> Remember me    </br>
    <input type="submit" value="Log in"/>
</form>
<button onclick="window.location.href = '/register';">Register</button>

</body>
</html>

