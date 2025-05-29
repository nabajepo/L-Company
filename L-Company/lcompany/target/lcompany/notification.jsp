<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <head>
        <link rel="icon" type="image/png" href="projetImages/logo.png">
        <title>Notification Page </title>
        <link rel="stylesheet" href="style.css">
    </head>
</head>
<body>
        <!---ERROR PAGE-->
        <div class="error">
           <img src="projetImages/erreur.png" alt="Erreur image">
           <%  String errorType=(String) request.getAttribute("error");%>
           <p>
               Erreur reconnu : <%= errorType %>
           </p>
        </div>
</body>
</html>
