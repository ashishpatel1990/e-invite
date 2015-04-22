<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="events" method="post" enctype="multipart/form-data">
Event Name <form:input path="event_name" /> <b><form:errors path="event_name" cssStyle="color:red;"/></b><br />
Event Message <form:input path="event_message" /><b><form:errors path="event_message" cssStyle="color:red;"/></b> <br />
Picture: <input type="file" name="pic" id="pic"/></b><br/>
<input type="submit" name="Create" value="Create" /> <br />
</form:form>
</body>
</html>