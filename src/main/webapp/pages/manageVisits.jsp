<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="template/header.jsp" />

<h1>${ad.title}</h1>
<h2>Scheduled Visits</h2>

<table class="text-center table table-hover">
	<tr>
		<th class="text-center"> Date</th>
		<th class="text-center"> Time</th>
		<th class="text-center"> Number of visitors</th>
		<th class="text-center"> Visitors</th>
	</tr>
	<c:forEach items="${timeSlots}" var="t">
		<tr>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${t.dateTime}" var="showDate"/>
			<fmt:formatDate pattern="HH.mm" value="${t.dateTime}" var="showTime"/>
			<td>${showDate}</td>
			<td>${showTime}</td>
			<td>${t.maxNumVisitors-t.placesLeft}/${t.maxNumVisitors}</td>
			<td><c:forEach items="${t.visitors}" var="v">
				${v.lastName} ${v.firstName}<br/>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
	<tr>
	
</table>
<a href="/timeslots/${category}/${adId}" class = "btn btn-green" role="button">Add Timeslots</a>
<a href="/placedAds" class = "btn btn-grey" role="button">Back</a>	

<script>
	document.getElementById('ad').style.color = '#ACCB12'
</script>


<c:import url="template/footer.jsp" />