<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome ${user}

<security:authorize access="isAnonymous()">
<br><a href= "/envite/spring_security_login" >Login</a><br/>
 <a href="Signup.html">Signup</a>

</security:authorize>

<security:authorize access="isAuthenticated()">
<h2 align="left"><p><a href="Create.html?id=${user}">Create Event</a></p></h2>
<p><a href="View1.html?id=${user}">Display Event</a></p>
<a href="/envite/j_spring_security_logout ">Logout</a>

</security:authorize>



</body>
</html>