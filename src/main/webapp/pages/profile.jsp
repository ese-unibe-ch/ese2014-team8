<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="template/header.jsp" />
<div class="col-sm-4 text-center">
<c:choose>
	<c:when test="${user.person.imageSaved}">
		<img alt="profile picture" class="resp-img" src="../profileImg/${user.person.id}.jpg">
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${fn:contains(profile.sex , 'm') }">
				<img alt="profile picture" class="resp-img" src="../img/male_user.jpg">
			</c:when>
			<c:when test="${fn:contains(profile.sex, 'f') }">
				<img alt="profile picture" class="resp-img" src="../img/female_user.jpg">
			</c:when>
			<c:otherwise>
				<img alt="profile picture" class="resp-img" src="../img/other_user.jpg">
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>


<div id="uploadImg" style ="display:none;">
	<c:if test="${profile.person.imageSaved}"><a href="/removeProfileImage/${profile.person.id}" class="btn btn-danger">Remove</a></c:if>
	<form method="POST" action="/profileImage" enctype="multipart/form-data">
       	Upload new profile picture: <input type="file" name="file" class="center-block text-center"/>
        <input type="submit" value="Upload" class= "btn btn-green pull-right"role="button"/> 
    </form>

</div>

</div>
<div class="col-sm-8">
<c:if test="${not empty message}">
<div class="alert alert-rentr" role="alert">${message}</div>
</c:if>
<h1>${profile.firstName} ${profile.lastName}</h1>

<p><b>E-Mail:</b> ${profile.email}</p>
<div id="viewProfile">
<p>
<b>First name:</b> ${profile.firstName} <br/>
<b>Last name:</b> ${profile.lastName} <br/>
<b>Age:</b> ${profile.age} <br/>
<b>Sex:</b> <c:choose><c:when test="${fn:contains(profile.sex , 'm') }">Male</c:when><c:when test="${fn:contains(profile.sex, 'f') }">Female</c:when><c:when test="${fn:contains(profile.sex, 'o') }">Other</c:when></c:choose>  <br/>
</p>
<p>
<b>Description:</b> <br/>
${profile.description}
</p>
<div class="btn btn-green" onclick="setEdit()">Edit</div>

</div>


<div id="editProfile" style ="display:none;">
<form:form method="post"  modelAttribute="profileForm" action="/saveProfile" id="profileForm" cssClass="form-horizontal"  autocomplete="off">
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
                <form:radiobutton path="sex" tabindex="5" value="m"/>Male 
				<form:radiobutton path="sex"  value="f"/>Female
				<form:radiobutton path="sex"  value="o"/>Other
            </div>
        </div>
        
        <c:set var="descriptionErrors"><form:errors path="description"/></c:set>
        <div class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
            <label class="control-label" for="field-description">Description</label>
            <div class="controls">
                <form:textarea path="description" id="field-description" tabindex="6" rows="10" cols="50"/>
                <form:errors path="description" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-green" tabindex="8">Save</button>
            <a href="/profile" role="button" class="btn btn-grey" tabindex="9">Cancel</a>
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
</div>
</div>
<script>
function setEdit(){
	document.getElementById('viewProfile').style.display= 'none';
	document.getElementById('editProfile').style.display= 'block';
	document.getElementById('uploadImg').style.display= 'block';
	
}
</script>

<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>
<c:import url="template/footer.jsp" />