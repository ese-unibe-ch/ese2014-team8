<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="template/header.jsp" />

<div>
	<h1>${message.subject}</h1>
	<p><strong>From:</strong> ${message.sender.lastName} ${message.sender.firstName}</p>
	<p><strong>To:</strong> ${message.receiver.lastName} ${message.receiver.firstName}</p>
	<p><strong>Message:</strong> <br/> 
	${message.content}</p>
</div>
<div>
	<h3>Reply</h3>
	<form:form method="post" modelAttribute="messageForm" action="/reply" cssClass="form-horizontal"  autocomplete="off">
		<c:choose>
			<c:when test="${message.ap != null}">
				<form:hidden path="category" value="Apartment"/>
				<form:hidden path="adId" value="${message.ap.id}"/>
			</c:when>
			<c:when test="${message.shAp != null}">
				<form:hidden path="category" value="Shared Apartment"/>
				<form:hidden path="adId" value="${message.shAp.id}"/>
			</c:when>
		</c:choose>
		<form:hidden path="senderId" value="${user.id}"/>
		<c:choose>
			<c:when test="${user.id==message.sender.id}">
				<form:hidden path="receiverId" value="${message.receiver.id}"/>
			</c:when>
			<c:otherwise>
				<form:hidden path="receiverId" value="${message.sender.id}"/>
			</c:otherwise>
		</c:choose>
		<label class="control-label" for="field-subject">Subject: </label>
		<form:input path="subject" id="field-subject" value="RE: ${message.subject }"/><br/>
		<label class="control-label" for="field-message">Message: </label><br/>
		<form:textarea path="message" id="field-message" rows="5" cols="30" tabindex="1"/><br/>
		<button type="submit" class="btn btn-green" tabindex="2">Send</button>
	</form:form>
</div>	

<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>


<c:import url="template/footer.jsp" />