<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>Search Results</h1>

<c:forEach items="${searchResults}" var="s">
    <a href="searchresults/${s.id}"><c:out value="${s.title}"/></a> </br>   
</c:forEach>

<c:import url="template/footer.jsp" />