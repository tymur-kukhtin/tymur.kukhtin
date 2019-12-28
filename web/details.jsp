<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User management/Details</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/details" method="post">
    <table>
        <tr>
            <td>Id</td>
            <td>${user.id}</td>
        </tr>
        <tr>
            <td>First name</td>
            <td>${user.firstName}</td>
        </tr>
        <tr>
            <td>Last name</td>
            <td>${user.lastName}</td>
        </tr>
        <tr>
            <td>Date of birth</td>
            <td><fmt:formatDate value="${user.dateOfBirth}" pattern="dd/MM/yyyy"/></td>
        </tr>
    </table>
    <input type="submit" name="okButton" value="Ok">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}]")
    </script>
</c:if>
</body>
</html>