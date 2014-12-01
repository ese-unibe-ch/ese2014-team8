<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h2>${ad.title}</h2>
<div>
	${ad.address.street} ${ad.address.number}<br/>
	${ad.address.zipCode} ${ad.address.city}
</div>
<div>
	<iframe
		width="400"
		height="400"
		frameborder="0" style="border:0"
		src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAg7gQ_6H5xWUXFVrxyUpulXzs3flqGfcA
			&q=${ad.address.street}+${ad.address.number},${ad.address.zipCode}+${ad.address.city},Switzerland">
	</iframe>
</div>
<div>
	<c:if test="${ad.fixedMoveIn==false}"> There is no fixed move-in date.<br/> </c:if>
	<c:if test="${ad.fixedMoveIn==true}"> Move-in date: <fmt:formatDate pattern="dd/MM/yyyy" value="${ad.moveIn}" /> <br/></c:if>
	<c:if test="${ad.fixedMoveOut==false}"> There is no fixed move-out date. <br/></c:if>
	<c:if test="${ad.fixedMoveOut==true}"> Move-out date: <fmt:formatDate pattern="dd/MM/yyyy" value="${ad.moveOut}" /><br/></c:if>
	Price: ${ad.price} chf<br/>
	Room size: ${ad.roomSize}<br/>
	
</div>
<div>
	Description: <br/>
	<c:out value="${ad.description}"/>
</div>
<div>
	Distance to Public Transport: ${ad.distanceToPubTr} meters.<br/>
	Distance to Shops: ${ad.distanceToShop} meters.<br/>
	Distance to Park: ${ad.distanceToPark} meters.<br/>
	Distance to School: ${ad.distanceToSchool} meters.<br/>
</div>
<div>
	<b>Tags: </b>
	<c:if test="${ad.tags.smokingAllowed}"><div class="label label-default" id="smokingAllowed">Smoking Allowed</div></c:if>
    <c:if test="${ad.tags.petsAllowed}"><div class="label label-default" id="petsAllowed">Pets Allowed</div></c:if>
    <c:if test="${ad.tags.musicInstrumentsAllowed}"><div class="label label-default" id="musicInstrumentsAllowed">Musical Instruments Allowed</div></c:if>
    <c:if test="${ad.tags.bikeParking}"><div class="label label-default" id="bikeParking">Bike-parking</div></c:if>
    <c:if test="${ad.tags.carParking}"><div class="label label-default" id="carParking">Car-parking</div></c:if>
    <c:if test="${ad.tags.sharedGarden}"><div class="label label-default" id="sharedGarden">Shared Garden</div></c:if>
    <c:if test="${ad.tags.balcony}"><div class="label label-default" id="balcony" >Balcony</div></c:if>
    <c:if test="${ad.tags.quietNeighbourhood}"><div class="label label-default" id="quietNeighbourhood" >Quiet Neighbourhood</div></c:if>
    <c:if test="${ad.tags.elevator}"><div class="label label-default" id="elevator" >Elevator</div></c:if>
    <c:if test="${ad.tags.wheelchairAccessible}"><div class="label label-default" id="wheelchairAccessible">Wheelchair Accessible</div></c:if>
    <c:if test="${ad.tags.lowEnergyBuilding}"><div class="label label-default" id="lowEnergyBuilding">Low Energy Building</div></c:if>
     
    <c:if test="${ad.tags.eatingCookingTogether}"><div class="label label-default" id="eatingCookingTogether" >Eating/Cooking Together</div></c:if>
    <c:if test="${ad.tags.stayingWeekends}"><div class="label label-default" id="stayingWeekends" >Staying Weekends</div></c:if>
    <c:if test="${ad.tags.vegetarianVegan}"><div class="label label-default" id="vegetarianVegan" >Vegetarian/Vegan</div></c:if>
    <c:if test="${ad.tags.nonVegetarian}"><div class="label label-default" id="nonVegetarian" >Non-vegetarian</div></c:if>
</div>
<div>
<h2>Visit this apartment</h2>
	<table class="text-center table table-hover">
		<tr>
			<th class="text-center"> Date</th>
			<th class="text-center"> Time</th>
			<th class="text-center"> Places left</th>
			<th></th>
		</tr>
		<c:forEach items="${ad.visitingTimes}" var="t">
			<tr>
				<fmt:formatDate pattern="dd/MM/yyyy" value="${t.dateTime}" var="showDate"/>
				<fmt:formatDate pattern="HH:mm" value="${t.dateTime}" var="showTime"/>
				<td>${showDate}</td>
				<td>${showTime}</td>
				<td>${t.placesLeft}</td>
				<td>
				<c:choose>
				<c:when test="${t.placesLeft ne 0}">
				<a href="/registerTimeslot/<c:if test="${not empty t.apartment}">Apartment/${t.apartment.id}</c:if>
						<c:if test="${not empty t.shApartment}">Shared Apartment/${t.shApartment.id}</c:if>/${t.id}" 
						class = "btn btn-green btn-block"role="button">Register</a>
				</c:when>	
				<c:otherwise>
				<button class="btn btn-block btn disabled" disabled="disabled">Full!</button>
				</c:otherwise>	
				</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

        <h1>Roommate-List</h1>

			<c:forEach items="${ad.roomMates}" var="mate">
				
				<a href="/showProfile/${mate.person.id}">${mate.firstName} ${mate.lastName}</a></br> 
				
			</c:forEach>
			
		</table>

<div>
	<h2>Message to ad owner</h2>
	<form:form method="post" modelAttribute="messageForm" action="/sendMessage" cssClass="form-horizontal"  autocomplete="off">
		<form:hidden path="category" value="Shared Apartment"/>
		<form:hidden path="adId" value="${ad.id}"/>
		<form:hidden path="senderId" value="${user.id}"/>
		<form:hidden path="receiverId" value="${ad.owner.id}"/>
		<form:hidden path="subject" value="Enquiry for apartment ${ad.id}: ${ad.title}"/>
		<form:textarea path="message" rows="5" cols="30" tabindex="1"/>
		<button type="submit" class="btn btn-green" tabindex="2">Send</button>
	</form:form>
</div>