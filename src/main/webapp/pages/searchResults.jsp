<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<c:import url="searchForm.jsp" />

<c:if test="${not empty searchResults}">
<h1>Search Results</h1>
<table class="text-center table table-hover">
	
	<c:forEach items="${searchResults}" var="s">
		
		<tr>
			<td><c:if test="${s.numberOfImages != 0 }"><a href="searchresults/${category}/${s.id}"><img src="../<c:if test="${category == 'Apartment' }">ApartmentImages</c:if>
																	<c:if test="${category == 'Shared Apartment' }">ShApartmentImages</c:if>/${s.id}_1.jpg" class="search-image"/></a></c:if></td>
    		<td><a href="searchresults/${category}/${s.id}"><c:out value="${s.title}"/><br/>
    			${s.description}</a>
    		</td>
    		<td><c:out value="CHF ${s.price}"/></td>
  		</tr>
	</c:forEach>  
</table>
</c:if>

<c:if test="${empty searchResults }">
	No ads match your search criteria.
</c:if>


<script>
	document.getElementById('search').style.color = '#ACCB12'
</script>
 


<c:import url="template/footer.jsp" />