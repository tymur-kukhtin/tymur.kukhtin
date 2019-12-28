<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User management/Add</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/add" method="post">
    First name <input type="text" name="firstName" value=""><br>
    Last name <input type="text" name="lastName" value=""><br>
    Date of birth <input type="text" name="date" value=""><br>
    <input type="submit" name="okButton" value="Ok">
    <input type="submit" name='cancelButton' value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}")
    </script>
</c:if>
</body>
</html>
