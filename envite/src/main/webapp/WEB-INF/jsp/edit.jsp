<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Event</title>
</head>
<body>

<form:form modelAttribute="events" method="post" enctype="multipart/form-data">
<table border="1">
<img src="${events.eid }.html"  />
Replace Picture: <input type="file" name="pic" id="pic"/></b><br/>
<tr><td>EventName <form:input path="event_name"/> <br/></td></tr>
<tr><td>EventMessage <form:input path="event_message" /></td></tr>
</table>
<input type="submit" name="save" value="Save" /> <br />
</form:form>

<form:form modelAttribute="invite">
<table border="1">
<tr><td>GUEST-NAME</td><td>GUEST-EMAIL</td></tr>
<c:forEach items="${invite}" var="user">
<tr>
  <td>${user.guestName}</td>
  <td>${user.guestEmail}</td>
  <td><a href="editGuest.html?gid=${user.invite_id }">Edit</a></td>
  <td><a href="Delete.html?gid=${user.invite_id }">Delete</a></td>
  
</tr>
</c:forEach>
<a href="addGuest.html">Add Guest</a>
</table>

</form:form>

</body>
</html>