<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><td>Event Name</td><td>Message</td></tr>
<c:forEach items="${events}" var="user">
<tr>
  <td>${user.event_name}</td>
  <td>${user.event_message }</td>
  <security:authorize access="isAuthenticated() and principal.username == '${user.users.username}'">
  <td> <a href="Details.html?eid=${user.eid}">details</a></td>
  
	<td> <a href="edit.html?eid=${user.eid}">EDIT</a></td>
	
<td> <a href="Email.html?eid=${user.eid}">Email</a></td>
</security:authorize>
 </tr>
</c:forEach>
</table>
<a href="Display.html">Back</a>

</body>
</html>