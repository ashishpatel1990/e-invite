<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
    <script type="text/javascript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
   <link href="<c:url value="/resources/core/main.css" />" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add guest</title>
</head>
<body>
<form:form modelAttribute="invite">
<table>
    <tr>
Guest Name: <form:input path="guestName" id="guestName"/> <b><form:errors path="guestName" cssStyle="color:red;"/> </b><br />
</tr>
<tr>
Guest Email: <form:input path="guestEmail" id="guestEmail"/><b><form:errors path="guestEmail" cssStyle="color:red;"/> </b> <br />
</tr>
<tr>
<input type="submit" name="addGuest" value="Add Guest" /> <br />
</tr>
</form:form>

<script type="text/javascript">

function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

$(document).ready(function(){
	 	
			$( "#guestName").autocomplete
			({
		        source: function (request, response)
		        {
$.getJSON("${pageContext. request. contextPath}/getTags.html", {term: extractLast(request.term)}, function(result)
		{response($.map(result, function(invite) { return {
            label: invite.guestName,
            value: invite.guestEmail,
            tag_url: "http://" + window.location.host + "/tags/" + item.invite_id + "/" + item.guestName
        };
    }));
	});
		        },
		        search: function () {
		            // custom minLength
		            var term = extractLast(this.value);
		            if (term.length < 1) {
		                return false;
		            }
		        },
		        focus: function () {
		            // prevent value inserted on focus
		            return false;
		        },
		        select: function (event, ui) {
		            var terms = split(this.value);
		            // remove the current input
		            terms.pop();
		            // add the selected item
		            terms.push(ui.item.label);
		            // add placeholder to get the comma-and-space at the end
		            this.value = terms.join(", ");
		            $("#guestEmail").val(ui.item.value);
			          
		            return false;
		        },
		        
	 });
 
  });

  </script>

</body>
</html>