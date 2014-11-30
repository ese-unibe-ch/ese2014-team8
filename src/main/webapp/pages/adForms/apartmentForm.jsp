<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="apartmentForm">
<form:form  method="post" modelAttribute="apForm" action="viewAd" id="apForm" cssClass="form-horizontal"  autocomplete="off" >
    <fieldset>
		<form:hidden path="category" value="Apartment"/>
		<form:hidden path="id"/>
		
		<legend>Title</legend>
        <c:set var="titleErrors"><form:errors path="title"/></c:set>
        <div class="control-group<c:if test="${not empty titleErrors}"> error</c:if>">
            <div class="controls">
                <form:input path="title" id="field-title" tabindex="1" maxlength="75" placeholder="Title"/>
                <form:errors path="title" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<legend>Location</legend>
        <c:set var="streetErrors"><form:errors path="street"/></c:set>
        <div class="control-group<c:if test="${not empty streetErrors}"> error</c:if>">
            <label class="control-label" for="field-street">Street</label>
            <div class="controls">
                <form:input path="street" id="field-street" tabindex="2" maxlength="50"  placeholder="Street"/>
                <form:errors path="street" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="numberErrors"><form:errors path="number"/></c:set>
        <div class="control-group<c:if test="${not empty numberErrors}"> error</c:if>">
            <label class="control-label" for="field-number">Nr.</label>
            <div class="controls">
                <form:input path="number" id="field-number" tabindex="3" maxlength="5" />
                <form:errors path="number" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="zipCodeErrors"><form:errors path="zipCode"/></c:set>
        <div class="control-group<c:if test="${not empty zipCodeErrors}"> error</c:if>">
            <label class="control-label" for="field-zipCode">ZIP-Code</label>
            <div class="controls">
                <form:input path="zipCode" id="field-zipCode" tabindex="4" maxlength="5"/>
                <form:errors path="zipCode" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="cityErrors"><form:errors path="city"/></c:set>
        <div class="control-group<c:if test="${not empty cityErrors}"> error</c:if>">
            <label class="control-label" for="field-city">City</label>
            <div class="controls">
                <form:input path="city" id="field-city" tabindex="5" maxlength="35"  placeholder="City"/>
                <form:errors path="city" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToPubTrErrors"><form:errors path="distanceToPubTr"/></c:set>
        <div class="control-group<c:if test="${not empty distanceToPubTrErrors}"> error</c:if>">
            <label class="control-label" for="field-distanceToPubTr">Distance to public transport</label>
            <div class="controls">
                <form:input path="distanceToPubTr" id="field-distanceToPubTr" tabindex="6" maxlength="5" placeholder="m"/>
                <form:errors path="distanceToPubTr" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToShopErrors"><form:errors path="distanceToShop"/></c:set>
        <div class="control-group<c:if test="${not empty distanceToShopErrors}"> error</c:if>">
            <label class="control-label" for="field-distanceToShop">Distance to shops</label>
            <div class="controls">
                <form:input path="distanceToShop" id="field-distanceToShop" tabindex="7" maxlength="5" placeholder="m"/>
                <form:errors path="distanceToShop" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToParkErrors"><form:errors path="distanceToPark"/></c:set>
        <div class="control-group<c:if test="${not empty distanceToParkErrors}"> error</c:if>">
            <label class="control-label" for="field-distanceToPark">Distance to park</label>
            <div class="controls">
                <form:input path="distanceToPark" id="field-distanceToPark" tabindex="8" maxlength="5" placeholder="m"/>
                <form:errors path="distanceToPark" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToSchoolErrors"><form:errors path="distanceToSchool"/></c:set>
        <div class="control-group<c:if test="${not empty distanceToSchoolErrors}"> error</c:if>">
            <label class="control-label" for="field-distanceToSchool">Distance to School</label>
            <div class="controls">
                <form:input path="distanceToSchool" id="field-distanceToSchool" tabindex="9" maxlength="5" placeholder="m"/>
                <form:errors path="distanceToSchool" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<legend>Rent Details</legend>
		<c:set var="priceErrors"><form:errors path="price"/></c:set>
        <div class="control-group<c:if test="${not empty priceErrors}"> error</c:if>">
            <label class="control-label" for="field-price">Price </label>
            <div class="controls">
                <form:input path="price" id="field-price" tabindex="10" maxlength="5"  placeholder="chf"/>
                <form:errors path="price" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		
		<div class="control-group" >
			<label  class="control-label"  for="field-fixedMoveIn">Fixed move-in date </label>
			
			<div class="controls">
				<form:checkbox id="field-fixedMoveIn" tabindex="11" path="fixedMoveIn" />
				
			</div>
		</div>
		
		<c:set var="moveInErrors"><form:errors path="moveIn"/></c:set>
        <div id="moveInJS" class="control-group<c:if test="${not empty moveInErrors}"> error</c:if>">
            <label class="control-label" for="field-moveIn">Move-in date</label>
            <div class="controls">
            	<fmt:formatDate pattern="dd/MM/yyyy" value="${apForm.moveIn}" var="simpleInDate"/>
                <form:input path="moveIn" id="field-moveIn" tabindex="12" maxlength="10" value="${simpleInDate}" placeholder="dd/MM/yyyy"  />
                <form:errors path="moveIn" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<div class="control-group">
			<label  class="control-label"  for="field-fixedMoveOut">Fixed move-out date </label>
			
			<div class="controls">
				<form:checkbox id="field-fixedMoveOut" tabindex="13" path="fixedMoveOut" />
				
			</div>
		</div>
		
		<c:set var="moveOutErrors"><form:errors path="moveOut"/></c:set>
        <div id="moveOutJS" class="control-group<c:if test="${not empty moveOutErrors}"> error</c:if>">
            <label class="control-label" for="field-moveOut">Move-out date </label>
            <div class="controls">
            	<fmt:formatDate pattern="dd/MM/yyyy" value="${apForm.moveOut}" var="simpleOutDate"/>
                <form:input path="moveOut" id="field-moveOut" tabindex="14" maxlength="10" value="${simpleOutDate}" placeholder="dd/MM/yyyy" />
                <form:errors path="moveOut" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<legend>Apartment Details</legend>
		
		<c:set var="numberOfRoomsErrors"><form:errors path="numberOfRooms"/></c:set>
        <div class="control-group<c:if test="${not empty numberOfRoomsErrors}"> error</c:if>">
            <label class="control-label" for="field-numberOfRooms">Number of rooms</label>
            <div class="controls">
                <form:input path="numberOfRooms" id="field-numberOfRooms" tabindex="15" maxlength="5"  placeholder="0"/>
                <form:errors path="numberOfRooms" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<c:set var="sizeErrors"><form:errors path="size"/></c:set>
        <div class="control-group<c:if test="${not empty sizeErrors}"> error</c:if>">
            <label class="control-label" for="field-size">Apartment size (m<sup>2</sup>)</label>
            <div class="controls">
                <form:input path="size" id="field-size" tabindex="16" maxlength="5"  />
                <form:errors path="size" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<c:set var="descriptionErrors"><form:errors path="description"/></c:set>
        <div class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
            <label class="control-label" for="field-description">Description</label>
            <div class="controls">
                <form:textarea path="description" id="field-description" tabindex="17" rows="10" cols="50"/>
                <form:errors path="description" cssClass="help-inline" element="span"/>
            </div>
        </div>
        </fieldset>
        <fieldset>
        <legend>Choose the tags describing your apartment</legend>
        <div class="text-center">
        <form:hidden path="tags.smokingAllowed"  id="field-smokingAllowed"/>
        <div class="btn <c:if test="${!apForm.tags.smokingAllowed}">btn-default</c:if><c:if test="${apForm.tags.smokingAllowed}">btn-green</c:if>" id="smokingAllowed" onclick="setTag(this.id)">Smoking Allowed</div>
        <form:hidden path="tags.petsAllowed"  id="field-petsAllowed"/>
        <div class="btn <c:if test="${!apForm.tags.petsAllowed}">btn-default</c:if><c:if test="${apForm.tags.petsAllowed}">btn-green</c:if>" id="petsAllowed" onclick="setTag(this.id)">Pets Allowed</div>
        <form:hidden path="tags.musicInstrumentsAllowed"  id="field-musicInstrumentsAllowed"/>
        <div class="btn <c:if test="${!apForm.tags.musicInstrumentsAllowed}">btn-default</c:if><c:if test="${apForm.tags.musicInstrumentsAllowed}">btn-green</c:if>" id="musicInstrumentsAllowed" onclick="setTag(this.id)">Musical Instruments Allowed</div>
        <form:hidden path="tags.bikeParking"  id="field-bikeParking"/>
        <div class="btn <c:if test="${!apForm.tags.bikeParking}">btn-default</c:if><c:if test="${apForm.tags.bikeParking}">btn-green</c:if>" id="bikeParking" onclick="setTag(this.id)">Bike-parking</div>
        <form:hidden path="tags.carParking"  id="field-carParking"/>
        <div class="btn <c:if test="${!apForm.tags.carParking}">btn-default</c:if><c:if test="${apForm.tags.carParking}">btn-green</c:if>" id="carParking" onclick="setTag(this.id)">Car-parking</div>
        <form:hidden path="tags.sharedGarden"  id="field-sharedGarden"/>
        <div class="btn <c:if test="${!apForm.tags.sharedGarden}">btn-default</c:if><c:if test="${apForm.tags.sharedGarden}">btn-green</c:if>" id="sharedGarden" onclick="setTag(this.id)">Shared Garden</div>
        <form:hidden path="tags.balcony"  id="field-balcony"/>
        <div class="btn <c:if test="${!apForm.tags.balcony}">btn-default</c:if><c:if test="${apForm.tags.balcony}">btn-green</c:if>" id="balcony" onclick="setTag(this.id)">Balcony</div>
        <form:hidden path="tags.quietNeighbourhood"  id="field-quietNeighbourhood"/>
        <div class="btn <c:if test="${!apForm.tags.quietNeighbourhood}">btn-default</c:if><c:if test="${apForm.tags.quietNeighbourhood}">btn-green</c:if>" id="quietNeighbourhood" onclick="setTag(this.id)">Quiet Neighbourhood</div>
        <form:hidden path="tags.elevator"  id="field-elevator"/>
        <div class="btn <c:if test="${!apForm.tags.elevator}">btn-default</c:if><c:if test="${apForm.tags.elevator}">btn-green</c:if>" id="elevator" onclick="setTag(this.id)">Elevator</div>
        <form:hidden path="tags.wheelchairAccessible"  id="field-wheelchairAccessible"/>
        <div class="btn <c:if test="${!apForm.tags.wheelchairAccessible}">btn-default</c:if><c:if test="${apForm.tags.wheelchairAccessible}">btn-green</c:if>" id="wheelchairAccessible" onclick="setTag(this.id)">Wheelchair Accessible</div>
        <form:hidden path="tags.lowEnergyBuilding" id="field-lowEnergyBuilding"/>
        <div class="btn <c:if test="${!apForm.tags.lowEnergyBuilding}">btn-default</c:if><c:if test="${apForm.tags.lowEnergyBuilding}">btn-green</c:if>" id="lowEnergyBuilding" onclick="setTag(this.id)">Low Energy Building</div>
        
        <form:hidden path="tags.kidFriendly"  id="field-kidFriendly"/>
        <div class="btn <c:if test="${!apForm.tags.kidFriendly}">btn-default</c:if><c:if test="${apForm.tags.kidFriendly}">btn-green</c:if>" id="kidFriendly" onclick="setTag(this.id)">Kid Friendly</div>
        <form:hidden path="tags.playgroundNearby" id="field-playgroundNearby"/>
        <div class="btn <c:if test="${!apForm.tags.playgroundNearby}">btn-default</c:if><c:if test="${apForm.tags.playgroundNearby}">btn-green</c:if>" id="playgroundNearby" onclick="setTag(this.id)">Playground Nearby</div>
        <form:hidden path="tags.onBusyRoad" id="field-onBusyRoad"/>
        <div class="btn <c:if test="${!apForm.tags.onBusyRoad}">btn-default</c:if><c:if test="$apForm.{tags.onBusyRoad}">btn-green</c:if>" id="onBusyRoad" onclick="setTag(this.id)">On Busy Road</div>
        </div>
        
		
        <div class="form-actions">
            <button type="submit" class="btn btn-green">Submit Ad</button>
            <button type="button" class="btn btn-grey">Cancel</button>
        </div>
    </fieldset>
</form:form>
</div>


<script>
	function setTag(elementId){
		if( document.getElementById('field-' + elementId).value == 'false'){
			document.getElementById('field-' + elementId).value = 'true';
			document.getElementById(elementId).className = 'btn btn-green';		
		}
		else{
			document.getElementById('field-' + elementId).value = 'false';
			document.getElementById(elementId).className = 'btn btn-default';
		}
		
	}
</script>