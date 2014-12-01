<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />
<div class="col-sm-2"></div>
<div class="col-sm-8">
<h1>Welcome to <b>RentR</b>! </h1>
<h2> Please complete your information before proceeding.</h2>

<p><b>E-Mail:</b> ${user.email}</p>
<p>
<form:form method="post" modelAttribute="profileForm" action="saveNewProfile" id="profileForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>

        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
        <div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
            <label class="control-label" for="field-firstName">First Name</label>
            <div class="controls">
                <form:input path="firstName" id="field-firstName" tabindex="2" maxlength="35" />
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
                <form:radiobutton path="sex" tabindex="5" value="m"/>Male 
				<form:radiobutton path="sex" value="f"/>Female
				<form:radiobutton path="sex" value="o"/>Other
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-green" tabindex="6">Save</button>
            <a href="/" role="button" class="btn btn-grey" tabindex="7">Cancel</a>
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

</div>

<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>

<c:import url="template/footer.jsp" />