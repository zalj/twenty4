<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
</head>
<body>
    <h1>Register</h1>
    <form method="post">
        First Name: <input type="text" name="firstName" /> <br/>
        Last Name: <input type="text" name="lastName" /> <br/>
        Username: <input type="text" name="username" /> <br/>
        Password: <input type="password" name="password" /> <br/>

        <input type="submit" value="Register" />
    </form>
</body>
</html>