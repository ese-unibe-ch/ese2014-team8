<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>User #${user.id} (${user.firstName} ${user.lastName})</h1>

<p>E-Mail: ${user.email}</p>
<p><c:choose>
<c:when test="${user.team != null}">
Is member of the team ${user.team.teamname}
</c:when>
<c:otherwise>
Isn't member of a team.
</c:otherwise>
</c:choose></p>

<c:import url="template/footer.jsp" />