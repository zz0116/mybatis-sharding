<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
username: ${user.username}<br>
password: ${user.password}<br>
articles:<br>
<c:forEach items="${articles}" var="article">
    <tr>
        <th><c:out value="${article.title}"/></th>
        <br>
    </tr>
</c:forEach>
</body>
</html>