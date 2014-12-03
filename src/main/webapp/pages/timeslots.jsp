<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="template/header.jsp" />

<h1>Timeslots</h1>
<form:form method="post" modelAttribute="timeSlotForm" action="/../timeslots" cssClass="form-horizontal"  autocomplete="off">
	<form:hidden path="category"/>
	<form:hidden path="adId"/>

<table class="text-center table table-hover">
	<tr>
		<th class="text-center"> Date</th>
		<th class="text-center"> Time</th>
		<th class="text-center"> Maximum number<br/> of visitors</th>
		<th></th>
	</tr>
	<c:forEach items="${timeSlots}" var="t">
		<tr>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${t.dateTime}" var="showDate"/>
			<fmt:formatDate pattern="HH:mm" value="${t.dateTime}" var="showTime"/>
			<td>${showDate}</td>
			<td>${showTime}</td>
			<td>${t.maxNumVisitors}</td>
			<td><c:choose>
				<c:when test="${t.maxNumVisitors == t.placesLeft }">
					<a href="/removeTimeslot/<c:if test="${not empty t.apartment}">Apartment/${t.apartment.id}</c:if>
						<c:if test="${not empty t.shApartment}">Shared Apartment/${t.shApartment.id}</c:if>/${t.id}" 
						class = "btn btn-danger btn-block"role="button">Remove</a>
				</c:when>
				<c:otherwise>
					<div class = "btn btn-danger btn-block disabled" role="button">Remove</button>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${timeSlotForm.date}" var="simpleDate"/>
            <form:input path="date" id="field-date" tabindex="1" maxlength="10" value="${simpleDate}" placeholder="dd/MM/yyyy"  />
		</td>
		<td>
			<fmt:formatDate pattern="HH:mm" value="${timeSlotForm.time}" var="simpleTime"/>
            <form:input path="time" id="field-time" tabindex="2" maxlength="10" value="${simpleTime}" placeholder="HH:mm (24h-notation)"  />
		</td>
		<td>
			<form:input id="field-maxNumVisitors" path="maxNumVisitors" tabindex="3"/>
		</td>
		<td>
			<button type="submit" class="btn btn-green btn-block" tabindex="4">Submit Time Slot</button>
		</td>
	</tr>
	
</table>
<a href="/viewAd/${timeSlotForm.category}/${timeSlotForm.adId}" class = "btn btn-grey" role="button">Finished</a>
	
	

</form:form>
<script>
	document.getElementById('ad').style.color = '#ACCB12'
</script>


<c:import url="template/footer.jsp" />