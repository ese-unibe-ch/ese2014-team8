<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="template/header.jsp" />

<h1>Upcoming visits</h1>

<table class="text-center table table-hover">
	<tr>
		<th class="text-center"> Ad</th>
		<th class="text-center"> Date</th>
		<th class="text-center"> Time</th>
		<th class="text-center"> Number of visitors</th>
		
	</tr>
	<c:forEach items="${user.registeredTimeSlots}" var="t">
		<c:if test="${t.dateTime.time > now.time }">
		<tr>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${t.dateTime}" var="showDate"/>
			<fmt:formatDate pattern="HH:mm" value="${t.dateTime}" var="showTime"/>
			<c:if test="${not empty t.apartment}">
			<td><a href="/searchresults/Apartment/{t.apartment.id}">${t.apartment.title}</a></td>
			</c:if>
			<c:if test="${not empty t.shApartment}">
			<td><a href="/searchresults/Shared Apartment/{t.shApartment.id}">${t.shApartment.title}</a></td>
			</c:if>
			<td>${showDate}</td>
			<td>${showTime}</td>
			<td>${t.maxNumVisitors-t.placesLeft}/${t.maxNumVisitors}</td>
		</tr>
		</c:if>
	</c:forEach>
	
</table>

<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>


<c:import url="template/footer.jsp" />