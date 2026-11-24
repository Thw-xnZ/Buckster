<%-- 
    Document   : erro
    Created on : 23 de nov. de 2026, 22:39:31
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Falha na operação</title>
    </head>
    <body>
        <%String msg = (String) request.getAttribute("message");%>
        <h1><%=msg%> não realizado</h1>
    </body>
</html>
