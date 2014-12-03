<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<legend>Your BookMarks</legend>
<c:if test="${not empty user.bookMarks}">
<table class="text-center table table-hover">
	<tr>
		<th class="text-center"> Apartment</th>
		<th class="text-center"> Shared Apartment</th>
		<th class="text-center"> </th>
	</tr>
	<c:forEach items="${user.bookMarks}" var="bookMark">
		<tr>
			<td>${bookMark.ap.title}</td>
			<td>${bookMark.shAp.title}</td>
 			<td>
  				<a href="/deleteBookMark/${bookMark.id}" class="btn btn-danger" role="button" >Delete </a> 
 			</td>  
		</tr>
	</c:forEach>
	
</table>
</c:if>	
	

