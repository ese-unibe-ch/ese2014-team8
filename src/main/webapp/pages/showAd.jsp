<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<c:if test="${category == 'Apartment'}">
	
	<c:if test="${!isBookMarked}">
		<a href="/addBookMark/${category}/${ad.id}" class="btn btn-green">Bookmark Ad</a>
	</c:if>
	<c:if test="${isBookMarked}">
		<div class="btn btn-green disabled">is already bookmarked!</div>
	</c:if>
	
	<c:import url="apartmentViews/viewApartmentAd.jsp" />
</c:if>

<c:if test="${category == 'Shared Apartment'}">

	<c:if test="${!isBookMarked}">
		<a href="/addBookMark/${category}/${ad.id}" class="btn btn-green">Bookmark Ad</a>
	</c:if>
	<c:if test="${isBookMarked}">
		<div >is already bookmarked!</div>
	</c:if>
	
	<c:import url="apartmentViews/viewSharedApartmentAd.jsp" />
</c:if>


<script>
	document.getElementById('search').style.color = '#ACCB12'
</script>
<c:import url="template/footer.jsp" />