<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h1>Roommate-List</h1>

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
  				<a href="/editRoomMate/${mate.id}" class="btn btn-green" role="button" >Edit </a> 
 			</td>
 			<td>
  				<a href="/deleteRoomMate/${mate.shApartment.id}/${mate.id}" class="btn btn-danger" role="button" >Delete </a> 
 			</td>  
		</tr>
	</c:forEach>
	
</table>
	
	




<c:import url="template/footer.jsp" />