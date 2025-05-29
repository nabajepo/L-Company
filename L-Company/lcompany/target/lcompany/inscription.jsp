<html>
    <head>
        <link rel="icon" type="image/png" href="projetImages/logo.png">
        <title>Page d'inscription</title>
        <link rel="stylesheet" href="style.css">
    </head>
<body>
    <form method="post" action="http://localhost:8080/lcompany/api/inscription/add">
        <div class="blockIII">
            <p>S'inscrire</p>
            <input type="text" name="name" placeholder="username (ne pas utiliser @)" required>
            <input type="email" name="email" placeholder="email" required>
            <input type="password" name="password" placeholder="password" required>
            <button type="submit">Inscription</button>
        </div>
     </form>
     
</body>
</html>    