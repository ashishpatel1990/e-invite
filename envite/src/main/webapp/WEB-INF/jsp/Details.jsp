<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="events">
<table border="1">
<img src="${events.eid }.html"  />
<tr><td>EventName <form:input path="event_name" /> <br/></td></tr>
<tr><td>EventMessage <form:input path="event_message" /> </td></tr>
</table>
</form:form>
<form:form modelAttribute="invite">
<table border="1">
<tr><td>GUEST-NAME</td><td>GUEST-EMAIL</td><td>STATUS</td></tr>
<c:forEach items="${invite}" var="user">
<tr>
  <td>${user.guestName}</td>
  <td>${user.guestEmail}</td>
 <c:choose>
  <c:when test="${user.accepted==null }">
  <td>Waiting</td>
  </c:when>
  <c:when test="${user.accepted!='' }">
  <td>${user.accepted }</td>
  </c:when>
  </c:choose>
</tr>
</c:forEach>
</table>
</form:form>
	<td><a href="Display.html">Back</a></td>
</body>
</html>