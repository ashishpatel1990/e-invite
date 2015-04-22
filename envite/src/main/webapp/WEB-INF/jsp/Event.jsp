<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><th>id</th><th>First_Name</th><th>Last_Name</th><th>Password</th><th>E-mail</th><th>UserName</th></tr>
<c:forEach items="${events}" var="event">
<tr>
  <td>${event.eid}</td>
  <td>${event.event_name}</td>
  <td>${event.event_message}</td>
  </tr>
</c:forEach>
</table>

<p><a href="Signup.html">Add A User</a></p>

</body>
</html>