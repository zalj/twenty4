<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spittr</title>
    <style type="text/css">
        div.errors{
            background-color: #ffcccc;
            border: 2px solid red;
        }
        label.error{
            color: red;
        }
    </style>
</head>
<body>
    <h1>Register</h1>
    <sf:form method="post" modelAttribute="spitter" enctype="multipart/form-data">
        <sf:errors path="*" element="div" cssClass="errors"/>
        <sf:label path="firstName" cssErrorClass="errors">First Name: </sf:label>
            <sf:input path="firstName" cssErrorClass="errors"/><br/>
        Last Name: <sf:input path="lastName"/><br/>
        Username: <sf:input path="username"/><br/>
        Password: <sf:password path="password"/><br/>
        Picture: <input type="file" name="picture-part"><br/>
        <input type="submit" value="Register" />
    </sf:form>
</body>
</html>