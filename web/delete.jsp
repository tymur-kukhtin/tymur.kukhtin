<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User management/Delete</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/delete" method="post">
    Are you sure? <br>
    <input type="submit" name="okButton" value="Ok">
    <input type="submit" name="cancelButton" value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}]")
    </script>
</c:if>
</body>
</html>