<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>User #${user.id} (${user.firstName} ${user.lastName})</h1>

<p>E-Mail: ${user.email}</p>
<p>
<form:form method="post" modelAttribute="profileForm" action="saveProfile" id="profileForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Enter Your Information</legend>

        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
        <div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
            <label class="control-label" for="field-firstName">First Name</label>
            <div class="controls">
                <form:input path="firstName" id="field-firstName" tabindex="2" maxlength="35" value="${user.firstName}"/>
                <form:errors path="firstName" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
        <div class="control-group<c:if test="${not empty lastNameErrors}"> error</c:if>">
            <label class="control-label" for="field-lastName">Last Name</label>
            <div class="controls">
                <form:input path="lastName" id="field-lastName" tabindex="3" maxlength="35" value="${user.lastName}"/>
                <form:errors path="lastName" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
         <c:set var="ageErrors"><form:errors path="age"/></c:set>
        <div class="control-group<c:if test="${not empty ageErrors}"> error</c:if>">
            <label class="control-label" for="field-age">Age</label>
            <div class="controls">
                <form:input path="age" id="field-age" tabindex="4" maxlength="3" value="${user.age}"/>
                <form:errors path="age" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="sexErrors"><form:errors path="sex"/></c:set>
        <div class="control-group<c:if test="${not empty sexErrors}"> error</c:if>">
            <label class="control-label" for="field-age">Sex</label>
            <div class="controls">
                <form:input path="sex" id="field-age" tabindex="4" maxlength="1" value="${user.sex}"/>
                <form:errors path="sex" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="descriptionErrors"><form:errors path="description"/></c:set>
        <div class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
            <label class="control-label" for="field-description">Description</label>
            <div class="controls">
                <form:textarea path="description" id="field-description" tabindex="13" rows="10" cols="50"/>
                <form:errors path="description" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Save</button>
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
</p>

<p><h2>Your Ads</h2></p>

<p><h3>Apartment Ads</h3>
<table>
<tr>
    <th>Title</th>
    <th>Price</th>
    <th>Address</th>
</tr>
<c:forEach items="${apartments}" var="apartment">
<tr>
    <td><a href="/searchresults/${apartment.id}"><b>${apartment.title}</b></a></td>
    <td>${apartment.price}</td>
    <td>${apartment.address.street} ${apartment.address.number}, ${apartment.address.zipCode} ${apartment.address.city}</td>
</tr>
</c:forEach>
</table>
</p>

<p><h3>Shared Apartment Ads</h3>
<table>
<tr>
    <th>Title</th>
    <th>Price</th>
    <th>Address</th>
</tr>
<c:forEach items="${shApartments}" var="shApartment">
<tr>
    <td><a href="/searchresults/${shApartment.id}"><b>${shApartment.title}</b></a></td>
    <td>${shApartment.price}</td>
    <td>${shApartment.address.street} ${shApartment.address.number}, ${shApartment.address.zipCode} ${shApartment.address.city}</td>
</tr>
</c:forEach>
</table>
</p>

<c:import url="template/footer.jsp" />