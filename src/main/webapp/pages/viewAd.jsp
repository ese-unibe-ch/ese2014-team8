<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<nav class="text-center">
	<ul class="pager">
		<li class="disabled"><span>1. Create Ad</span></li>
		<li class="active"><span>2. View Result</span></li>
		<li class="disabled"><span>3. Add Visiting Times</span></li>
	</ul>
</nav>
<div class="row">
<div class="col-sm-4 col-sm-push-8">

	<a href="/editAd/${category}/${ad.id}" class="btn btn-grey">Edit Ad</a>
    <a href="/main" class="btn btn-grey" role="button">Submit</a>
    <a href="/timeslots/${category}/${ad.id}" class="btn btn-green" role="button">Add visiting times</a>
</div>
   

<c:if test="${category == 'Apartment'}">
	
	<c:import url="apartmentViews/viewApartmentAd.jsp" />
</c:if>

<c:if test="${category == 'Shared Apartment'}">
	
	<c:import url="apartmentViews/viewSharedApartmentAd.jsp" />
</c:if>
		

<script>
	document.getElementById('ad').style.color = '#ACCB12'
</script>
<c:import url="template/footer.jsp" />