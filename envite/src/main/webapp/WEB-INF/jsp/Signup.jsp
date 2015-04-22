<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form:form modelAttribute="users">

<table border="1">
<tr><td>First Name: <form:input path="f_name" /><b><form:errors path="f_name" cssStyle="color:red;"/> </b> <br/>
</td></tr>
<tr><td>Last Name: <form:input path="l_name" /> <b><form:errors path="l_name" cssStyle="color:red;"/></b><br />
</td></tr>
<tr><td>Username: <form:input path="username" /> <b><form:errors path="username" cssStyle="color:red;"/></b><br />
</td></tr>
<tr><td>Password: <form:input path="password" /> <b><form:errors path="password" cssStyle="color:red;"/></b><br />
</td></tr>
<tr><td>E-mail id: <form:input path="user_email" /> <b><form:errors path="user_email" cssStyle="color:red;"/></b><br />
</td></tr>
</table>
<input type="submit" name="signup" value="sign" /> <br />
<form:form modelAttribute="authorizes">
</form:form>
</form:form>

</body>
</html>