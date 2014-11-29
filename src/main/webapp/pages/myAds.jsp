<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<p><h1>Your Ads</h1></p>

<a href="/newAd" class="btn btn-green">New Ad</a>

<p><h2>Apartment Ads</h2>
<table class="text-center table table-hover">
<tr>
    <th class="text-center">Title</th>
    <th class="text-center">Price</th>
    <th class="text-center">Address</th>
    <th></th>
    
</tr>
<c:forEach items="${apartments}" var="apartment">
<tr>
    <td><a href="/viewAd/Apartment/${apartment.id}"><b>${apartment.title}</b></a></td>
    <td>${apartment.price} chf</td>
    <td>${apartment.address.street} ${apartment.address.number}, ${apartment.address.zipCode} ${apartment.address.city}</td>
    <td class="text-right">
    	<a href="/" class="btn btn-grey " role="button">See who's interested</a>
    	<a href="/editAd/Apartment/${apartment.id}" class="btn btn-green  " role="button">Edit</a><br/>
    	<a href="/manageVisits/Apartment/${apartment.id}" class="btn btn-grey " role="button">My Visits</a>
    	<a href="/removeAd/Apartment/${apartment.id}" class="btn btn-danger  " role="button">Remove</a></td>
    
</tr>
</c:forEach>
</table>
</p>

<p><h2>Shared Apartment Ads</h2>
<table class="text-center table table-hover">
<tr>
    <th class="text-center">Title</th>
    <th class="text-center">Price</th>
    <th class="text-center">Address</th>
</tr>
<c:forEach items="${shApartments}" var="shApartment">
<tr>
    <td><a href="/viewAd/Shared Apartment/${shApartment.id}"><b>${shApartment.title}</b></a></td>
    <td>${shApartment.price} chf</td>
    <td>${shApartment.address.street} ${shApartment.address.number}, ${shApartment.address.zipCode} ${shApartment.address.city}</td>
</tr>
</c:forEach>
</table>
</p>
<script>
	document.getElementById('profile').style.color = '#ACCB12'
</script>

<c:import url="template/footer.jsp" />