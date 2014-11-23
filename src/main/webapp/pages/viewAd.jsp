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

<form:form method="post" modelAttribute="apartmentForm" action="editAd" id="apartmentForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>	
    	<form:hidden path="category" value="${category}"/>
		<form:hidden path="id" value="${ad.id}"/>
         <div class="form-actions">
            <button type="submit" class="btn btn-primary">Edit Ad</button>
            <a href="main" class="btn btn-default" role="button">Submit without adding visiting times</a>
            <a href="timeslots/${category}/${ad.id}" class="btn btn-default" role="button">Add visiting times</a>
        </div>
    </fieldset>
</form:form>
<c:import url="template/footer.jsp" />