<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<legend>Roommates</legend>
<c:if test="${not empty roomMates}">
<table class="text-center table table-hover">
	<tr>
		<th class="text-center"> First Name</th>
		<th class="text-center"> Last Name</th>
		<th class="text-center"> </th>
		<th class="text-center"> </th>
	</tr>
	<c:forEach items="${roomMates}" var="mate">
		<tr>
			<td>${mate.firstName}</td>
			<td>${mate.lastName}</td>
 			<td>
  				<a href="/editRoomMate/${mate.id}" class="btn btn-green btn-block" role="button" >Edit </a> 
 			</td>
 			<td>
  				<a href="/deleteRoomMate/${mate.shApartment.id}/${mate.id}" class="btn btn-danger btn-block" role="button" >Delete </a> 
 			</td>  
		</tr>
	</c:forEach>
	
</table>
</c:if>	
	

