<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />
<div class="col-sm-2"></div>
<div class="col-sm-8">
<c:if test="${not empty messageUpdate}">
<div class="alert alert-rentr" role="alert">${messageUpdate}</div>
</c:if>
<c:if test="${not empty upcomingVisits}">
<div class="alert alert-rentr" role="alert">${upcomingVisits}</div>
</c:if>

<c:import url="searchForm.jsp" />

<c:import url="template/footer.jsp" />
