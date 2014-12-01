<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="template/header.jsp" />

<h1>${profile.firstName} ${profile.lastName}</h1>

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

</div>

<c:import url="template/footer.jsp" />