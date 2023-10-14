<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittle <c:out value="${spittle.id}"/></title>
</head>
<body>
<div>
    <div class="spittleMessage">
        <c:out value="${spittle.message}"/>
    </div>
    <div>
        <span class="spittleTime"><c:out value="${spittle.time}"/></span>
        <span class="spittleLocation">
                    (<c:out value="${spittle.latitude}"/>
                    <c:out value="${spittle.longitude}"/>)
                </span>
    </div>
</div>
</body>
</html>