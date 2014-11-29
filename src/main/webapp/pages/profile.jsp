<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>User ${profile.firstName} ${profile.lastName}</h1>

<p>E-Mail: ${profile.email}</p>
<p>
<form:form method="post" enctype="multipart/form-data" modelAttribute="profileForm" action="saveProfile" id="profileForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Enter Your Information</legend>

        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
        <div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
            <label class="control-label" for="field-firstName">First Name</label>
            <div class="controls">
                <form:input path="firstName" id="field-firstName" tabindex="2" maxlength="35"/>
                <form:errors path="firstName" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
        <div class="control-group<c:if test="${not empty lastNameErrors}"> error</c:if>">
            <label class="control-label" for="field-lastName">Last Name</label>
            <div class="controls">
                <form:input path="lastName" id="field-lastName" tabindex="3" maxlength="35" />
                <form:errors path="lastName" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
         <c:set var="ageErrors"><form:errors path="age"/></c:set>
        <div class="control-group<c:if test="${not empty ageErrors}"> error</c:if>">
            <label class="control-label" for="field-age">Age</label>
            <div class="controls">
                <form:input path="age" id="field-age" tabindex="4" maxlength="3" />
                <form:errors path="age" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        
        <div class="control-group">
            <label class="control-label" for="field-age">Sex</label>
            <div class="controls">
                <form:radiobutton path="sex" value="m"/>Male 
				<form:radiobutton path="sex" value="f"/>Female
				<form:radiobutton path="sex" value="o"/>Other
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

        <c:set var="pictureErrors"><form:errors path="picture"/></c:set>
        <div class="control-group<c:if test="${not empty pictureErrors}"> error</c:if>">
            <label class="control-label" for="field-description">Picture</label>
            <img src="/getUserPicture/${user.id}"/>
            <div class="controls">
                <form:input type="file" path="picture" id="field-picture" tabindex="14" />
                <form:errors path="picture" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-green">Save</button>
            <button type="button" class="btn btn-default">Cancel</button>
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

<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>
<c:import url="template/footer.jsp" />