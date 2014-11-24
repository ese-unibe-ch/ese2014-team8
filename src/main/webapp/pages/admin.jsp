<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<p><a name="apartmentAds"/><h2>Open ads</h2></p>
<table>
    <tr><th>Ad title</th><th>Price</th><th>Address</th></tr>
    <c:forEach items="${apartmentAds}" var="apartment">
        <tr>
            <td><a href="/searchresults/${apartment.id}"><b>${apartment.title}</b></a></td>
            <td>${apartment.price}</td>
            <td>${apartment.address.street} ${apartment.address.number}, ${apartment.address.zipCode} ${apartment.address.city}</td>
        </tr>
    </c:forEach>
</table>

<p><a name="shApartmentAds"/><h2>Open shared apartment ads</h2></p>
<table>
    <tr><th>Ad title</th><th>Price</th><th>Address</th></tr>
    <c:forEach items="${shApartmentAds}" var="apartment">
        <tr>
            <td><a href="/searchresults/${apartment.id}"><b>${apartment.title}</b></a></td>
            <td>${apartment.price}</td>
            <td>${apartment.address.street} ${apartment.address.number}, ${apartment.address.zipCode} ${apartment.address.city}</td>
        </tr>
    </c:forEach>
</table>

<p><a name="profiles"/><h2>Profiles</h2></p>

<table>
    <tr><th>First name</th><th>Last name</th><th>Mail address</th></tr>
    <c:forEach items="${users}" var="userEntry">
        <tr>
            <td>${userEntry.firstName}</td>
            <td>${userEntry.lastName}</td>
            <td>${userEntry.email}</td>
        </tr>
    </c:forEach>
</table>

<c:import url="template/footer.jsp" />
