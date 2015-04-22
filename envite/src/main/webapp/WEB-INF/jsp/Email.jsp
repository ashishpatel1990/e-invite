<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
    function toggle(source) {
        var aInputs = document.getElementsByTagName('input');
        for (var i=0;i<aInputs.length;i++) {
            if (aInputs[i] != source && aInputs[i].className == source.className) {
                aInputs[i].checked = source.checked;
            }
        }
    }
 </script> 
</head>
<body>
<table border="1">
<form:form modelAttribute="events">
<input type="hidden" name="event" value="${eid}"/>

<form:form modelAttribute="invite">
<input type='checkbox' class="checkall" onClick='toggle(this)' />Select All<br />
<table border="1">
<c:set var="count" value="0" scope="page" />
<tr><td>GUEST-NAME</td></tr>
<c:forEach items="${invite}" var="user" >
<c:if test="${user.isStatus()!=true }">
<tr>
<c:set var="count" value="${count + 1}" scope="page"/>
  <td><input type="checkbox" class="checkall" name="to" value="${user.guestEmail}" />${user.guestName}</td> 
 </tr>
 </c:if>
</c:forEach>

<c:if test="${ count!=0}">
<td><input type="submit" name="send" value="Send" /></td>
</c:if>
</table>
	<td><a href="Display.html">Back</a></td>
</form:form>
</form:form>
</body>
</html>