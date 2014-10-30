<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Advertise your apartment here!</h1>


<form:form method="post" modelAttribute="adForm" action="makeAd" id="adForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
		<form:hidden path="id" value="${oldAd.id}"/>
		
		<legend>Title</legend>
        <c:set var="titleErrors"><form:errors path="title"/></c:set>
        <div class="control-group<c:if test="${not empty titleErrors}"> error</c:if>">
            <div class="controls">
                <form:input path="title" id="field-title" tabindex="1" maxlength="75" value="${oldAd.title}" placeholder="Title"/>
                <form:errors path="title" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<legend>Location</legend>
        <c:set var="streetErrors"><form:errors path="street"/></c:set>
        <div class="control-group<c:if test="${not empty streetErrors}"> error</c:if>">
            <label class="control-label" for="field-street">Street</label>
            <div class="controls">
                <form:input path="street" id="field-street" tabindex="2" maxlength="50" value="${oldAd.address.street}" placeholder="Street"/>
                <form:errors path="street" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="numberErrors"><form:errors path="number"/></c:set>
        <div class="control-group<c:if test="${not empty numberErrors}"> error</c:if>">
            <label class="control-label" for="field-number">Nr.</label>
            <div class="controls">
                <form:input path="number" id="field-number" tabindex="3" maxlength="5" value="${oldAd.address.number}"/>
                <form:errors path="number" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="zipCodeErrors"><form:errors path="zipCode"/></c:set>
        <div class="control-group<c:if test="${not empty zipCodeErrors}"> error</c:if>">
            <label class="control-label" for="field-zipCode">ZIP-Code</label>
            <div class="controls">
                <form:input path="zipCode" id="field-zipCode" tabindex="4" maxlength="5" value="${oldAd.address.zipCode}"/>
                <form:errors path="zipCode" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="cityErrors"><form:errors path="city"/></c:set>
        <div class="control-group<c:if test="${not empty cityErrors}"> error</c:if>">
            <label class="control-label" for="field-city">City</label>
            <div class="controls">
                <form:input path="city" id="field-city" tabindex="5" maxlength="35" value="${oldAd.address.city}" placeholder="City"/>
                <form:errors path="city" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<legend>Rent Details</legend>
		<c:set var="priceErrors"><form:errors path="price"/></c:set>
        <div class="control-group<c:if test="${not empty priceErrors}"> error</c:if>">
            <label class="control-label" for="field-price">Price (chf)</label>
            <div class="controls">
                <form:input path="price" id="field-price" tabindex="6" maxlength="5" value="${oldAd.price}" placeholder="0"/>
                <form:errors path="price" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<div class="control-group">
			<label  class="control-label"  for="field-price">Fixed move in date </label>
			
			<div class="controls">
				<form:checkbox path="fixedMoveIn" />
				
			</div>
		</div>
		
		<legend>Apartment Details</legend>
		<c:set var="numberOfRoomsErrors"><form:errors path="numberOfRooms"/></c:set>
        <div class="control-group<c:if test="${not empty numberOfRoomsErrors}"> error</c:if>">
            <label class="control-label" for="field-numberOfRooms">Number of rooms</label>
            <div class="controls">
                <form:input path="numberOfRooms" id="field-numberOfRooms" tabindex="7" maxlength="5" value="${oldAd.numberOfRooms}" placeholder="0"/>
                <form:errors path="numberOfRooms" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="descriptionErrors"><form:errors path="description"/></c:set>
        <div class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
            <label class="control-label" for="field-description">Description</label>
            <div class="controls">
                <form:textarea path="description" id="field-description" tabindex="8" rows="10" cols="50"/>
                <form:errors path="description" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Submit Ad</button>
            <button type="button" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>




	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
