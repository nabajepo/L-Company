<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <head>
        <link rel="icon" type="image/png" href="projetImages/logo.png">
        <title>Page Contact </title>
        <link rel="stylesheet" href="style.css">
    </head>
</head>
<body>
    <%
    ArrayList<String[]> users = (ArrayList<String[]>) request.getAttribute("usersList");
    if (users != null && !users.isEmpty()) {
        for (String[] user : users) {
    %>

        <div class="contact">
           <p>Nom : <%= user[0] %></p>
           <p>Email : <%= user[1] %></p>
        </div>
    <%
        }
    } else{
    %> 
         <div class="contactI">
            <p>Aucun utilisateur inscrit </p>
         </div>
    <%
    }
    %>    
   
</body>
</html>
