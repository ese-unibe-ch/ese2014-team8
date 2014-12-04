<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<c:if test="${category == 'Apartment'}">
	<h1>${message}</h1>
	<c:import url="apartmentViews/viewApartmentAd.jsp" />
</c:if>

<c:if test="${category == 'Shared Apartment'}">
	<h1>${message}</h1>
	<c:import url="apartmentViews/viewSharedApartmentAd.jsp" />
</c:if>
		<div>

            <a href="/editAd/${category}/${ad.id}" class="btn btn-grey">Edit Ad</a>
            <a href="/main" class="btn btn-grey" role="button">Submit</a>
            <a href="/timeslots/${category}/${ad.id}" class="btn btn-green" role="button">Add visiting times</a>
        </div>
   

<script>
	document.getElementById('ad').style.color = '#ACCB12'
</script>
<c:import url="template/footer.jsp" />