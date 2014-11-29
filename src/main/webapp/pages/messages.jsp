<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="template/header.jsp" />

<h1>Received Messages</h1>
<table class="text-center table table-hover"">
	<tr>
		<th class="text-center">From</th>
		<th class="text-center"> Subject</th>
		<th class="text-center"> Sent</th>
	</tr>
	<c:forEach items="${receivedMessages}" var="rM">
		<tr <c:if test="${!rM.messageRead}">class="emph"</c:if>>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${rM.dateTime}" var="showDate"/>
			<fmt:formatDate pattern="HH.mm" value="${rM.dateTime}" var="showTime"/>
			<td>${rM.sender.lastName} ${rM.sender.firstName}</td>
			<td><a href="/readMessage/${rM.id}">${rM.subject}</a></td>
			<td>${showDate} ${showTime}</td>
		</tr>
	</c:forEach>
</table>
<h1>Sent Messages</h1>
<table class="text-center table table-hover">
	<tr>
		<th class="text-center">To</th>
		<th class="text-center"> Subject</th>
		<th class="text-center"> Sent</th>
	</tr>
	<c:forEach items="${receivedMessages}" var="tM">
		<tr>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${tM.dateTime}" var="showDate"/>
			<fmt:formatDate pattern="HH.mm" value="${tM.dateTime}" var="showTime"/>
			<td>${tM.receiver.lastName} ${tM.receiver.firstName}</td>
			<td><a href="/readMessage/${tM.id}">${tM.subject}</a></td>
			<td>${showDate} ${showTime}</td>
		</tr>
	</c:forEach>
</table>	
	
<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>


<c:import url="template/footer.jsp" />