<%@ page import="am.bagiryan.myspringmvc.model.User" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 13.02.2020
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--    <title>${user.name} 's page</title>--%>
    <title>page</title>
</head>
<body>
<%
    if (request.getAttribute("prof") != null) {
        response.getWriter().println(request.getAttribute("prof"));
    }
%>

<% User user = (User) session.getAttribute("user"); %>
<p>Username: <%=user.getUsername()%></p>
<p>Name: <%=user.getName()%></p>
<p>Last Name:<%=user.getSurname()%></p>
<p>Age: <%=user.getAge()%></p>

<button onclick="window.location.href = '/delete';">Delete profile</button>
<button onclick="window.location.href = '/logout';">Logout</button>
</body>
</html>
