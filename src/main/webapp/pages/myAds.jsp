<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<p><h1>Your Ads</h1></p>

<a href="/newAd" class="btn btn-green">New Ad</a>

<p><h2>Apartment Ads</h2>
<table>
<tr>
    <th>Title</th>
    <th>Price</th>
    <th>Address</th>
</tr>
<c:forEach items="${apartments}" var="apartment">
<tr>
    <td><a href="/searchresults/Apartment/${apartment.id}"><b>${apartment.title}</b></a></td>
    <td>${apartment.price}</td>
    <td>${apartment.address.street} ${apartment.address.number}, ${apartment.address.zipCode} ${apartment.address.city}</td>
</tr>
</c:forEach>
</table>
</p>

<p><h2>Shared Apartment Ads</h2>
<table>
<tr>
    <th>Title</th>
    <th>Price</th>
    <th>Address</th>
</tr>
<c:forEach items="${shApartments}" var="shApartment">
<tr>
    <td><a href="/searchresults/Shared Apartment/${shApartment.id}"><b>${shApartment.title}</b></a></td>
    <td>${shApartment.price}</td>
    <td>${shApartment.address.street} ${shApartment.address.number}, ${shApartment.address.zipCode} ${shApartment.address.city}</td>
</tr>
</c:forEach>
</table>
</p>

<c:import url="template/footer.jsp" />