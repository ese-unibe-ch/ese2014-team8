<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<form:form  method="post" modelAttribute="apForm" action="viewApAd" id="apForm" cssClass="form-horizontal"  autocomplete="off" enctype="multipart/form-data">

<div class="row">
<div class="col-sm-8">
   <fieldset>
		<form:hidden path="category" value="Apartment"/>
		<form:hidden path="id"/>
		<form:hidden path="uploadedImages"/>
		
		
		<legend>Apartment Title *</legend>
        <c:set var="titleErrors"><form:errors path="title"/></c:set>
        <div class="form-group<c:if test="${not empty titleErrors}"> has-error</c:if>">
            
            <div class="controls">
                <form:input path="title" id="field-title" tabindex="1" maxlength="75" class="title-input form-control" placeholder=" Write a meaningful title containing the most improtant features of your property"/>
                <br/><form:errors path="title" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
</div>
<div class="col-sm-4 text-center">
	<label class="control-label"><br/><br/>(* Obligatory Fields)</label>
</div>

 </div> 
 <div class="row">
 <div class="col-sm-4">       
		
		<legend>Location</legend>
        <c:set var="streetErrors"><form:errors path="street"/></c:set>
        <div class="form-group<c:if test="${not empty streetErrors}"> has-error</c:if>">
            <label class="control-label" for="field-street">Street *</label>
            <div class="controls">
                <form:input path="street" id="field-street" tabindex="2" maxlength="50"  class="wide-input form-control" placeholder="Street"/>
                <form:errors path="street" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="numberErrors"><form:errors path="number"/></c:set>
        <div class="form-group<c:if test="${not empty numberErrors}"> has-error</c:if>">
            <label class="control-label" for="field-number">Nr. *</label>
            <div class="controls">
                <form:input path="number" id="field-number" class="wide-input form-control" tabindex="3" maxlength="5" />
                <form:errors path="number" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="zipCodeErrors"><form:errors path="zipCode"/></c:set>
        <div class="form-group<c:if test="${not empty zipCodeErrors}"> has-error</c:if>">
            <label class="control-label" for="field-zipCode">ZIP-Code *</label>
            <div class="controls">
                <form:input path="zipCode" id="field-zipCode" class="wide-input form-control" tabindex="4" maxlength="5"/>
                <form:errors path="zipCode" cssClass="help-inline" element="span"/>
            </div>
        </div>
		<c:set var="cityErrors"><form:errors path="city"/></c:set>
        <div class="form-group<c:if test="${not empty cityErrors}"> has-error</c:if>">
            <label class="control-label" for="field-city">City *</label>
            <div class="controls">
                <form:input path="city" id="field-city" class="wide-input form-control" tabindex="5" maxlength="35"  placeholder="City"/>
                <form:errors path="city" cssClass="help-inline" element="span"/>
            </div>
        </div>
</div>        
<div class="col-sm-4"> 
		<legend>Environment</legend>
		<h3><small>(distances in meter)</small></h3> 
        <c:set var="distanceToPubTrErrors"><form:errors path="distanceToPubTr"/></c:set>
        <div class="form-group<c:if test="${not empty distanceToPubTrErrors}"> has-error</c:if>">
            <label class="control-label" for="field-distanceToPubTr">Distance to public transport</label>
            <div class="controls">
                <form:input path="distanceToPubTr" id="field-distanceToPubTr" class="wide-input form-control" tabindex="6" maxlength="5" placeholder="meter"/>
                <form:errors path="distanceToPubTr" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToShopErrors"><form:errors path="distanceToShop"/></c:set>
        <div class="form-group<c:if test="${not empty distanceToShopErrors}"> has-error</c:if>">
            <label class="control-label" for="field-distanceToShop">Distance to shops</label>
            <div class="controls">
                <form:input path="distanceToShop" id="field-distanceToShop" tabindex="7" maxlength="5" placeholder="meter" class="wide-input form-control"/>
                <form:errors path="distanceToShop" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToParkErrors"><form:errors path="distanceToPark"/></c:set>
        <div class="form-group<c:if test="${not empty distanceToParkErrors}"> has-error</c:if>">
            <label class="control-label" for="field-distanceToPark">Distance to park</label>
            <div class="controls">
                <form:input path="distanceToPark" id="field-distanceToPark" tabindex="8" maxlength="5" placeholder="meter" class="wide-input form-control"/>
                <form:errors path="distanceToPark" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="distanceToSchoolErrors"><form:errors path="distanceToSchool"/></c:set>
        <div class="form-group<c:if test="${not empty distanceToSchoolErrors}"> has-error</c:if>">
            <label class="control-label" for="field-distanceToSchool">Distance to School</label>
            <div class="controls">
                <form:input path="distanceToSchool" id="field-distanceToSchool" tabindex="9" maxlength="5" placeholder="meter" class="wide-input form-control"/>
                <form:errors path="distanceToSchool" cssClass="help-inline" element="span"/>
            </div>
        </div>
</div>
<div class="col-sm-4">		
		<legend>Rent Details</legend>
		<c:set var="priceErrors"><form:errors path="price"/></c:set>
        <div class="form-group<c:if test="${not empty priceErrors}"> has-error</c:if>">
            <label class="control-label" for="field-price">Price (CHF) *</label>
            <div class="controls">
                <form:input path="price" id="field-price" tabindex="10" maxlength="5"  placeholder="chf" class="wide-input form-control"/>
                <form:errors path="price" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		
		<div class="form-group" >
		<br/>
			<label  class="control-label"  for="field-fixedMoveIn">Fixed move-in date:   </label>
			
			
				<form:checkbox id="field-fixedMoveIn" tabindex="11" path="fixedMoveIn" />
				
			
		</div>
		
		<c:set var="moveInErrors"><form:errors path="moveIn"/></c:set>
        <div id="moveInJS" class="form-group<c:if test="${not empty moveInErrors}"> has-error</c:if>">
            <label class="control-label" for="field-moveIn">Move-in date</label>
            <div class="controls">
            	<fmt:formatDate pattern="dd/MM/yyyy" value="${apForm.moveIn}" var="simpleInDate"/>
                <form:input path="moveIn" id="field-moveIn" tabindex="12" maxlength="10" value="${simpleInDate}" placeholder="dd/MM/yyyy"  class="wide-input form-control"/>
                <form:errors path="moveIn" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<div class="form-group">
		<br/>
			<label  class="control-label"  for="field-fixedMoveOut">Fixed move-out date:   </label>
			
			
				<form:checkbox id="field-fixedMoveOut" tabindex="13" path="fixedMoveOut"  />
				
			
		</div>
		
		<c:set var="moveOutErrors"><form:errors path="moveOut"/></c:set>
        <div id="moveOutJS" class="form-group<c:if test="${not empty moveOutErrors}"> has-error</c:if>">
            <label class="control-label" for="field-moveOut">Move-out date </label>
            <div class="controls">
            	<fmt:formatDate pattern="dd/MM/yyyy" value="${apForm.moveOut}" var="simpleOutDate"/>
                <form:input path="moveOut" id="field-moveOut" tabindex="14" maxlength="10" value="${simpleOutDate}" placeholder="dd/MM/yyyy" class="wide-input form-control"/>
                <form:errors path="moveOut" cssClass="help-inline" element="span"/>
            </div>
        </div>
</div>
</div>
<div class="row">
<div class="col-sm-6">		
		<legend>Apartment Details</legend>
		
		<c:set var="numberOfRoomsErrors"><form:errors path="numberOfRooms"/></c:set>
        <div class="form-group<c:if test="${not empty numberOfRoomsErrors}"> has-error</c:if>">
            <label class="control-label" for="field-numberOfRooms">Number of rooms *</label>
            <div class="controls">
                <form:input path="numberOfRooms" id="field-numberOfRooms" tabindex="15" maxlength="5"  placeholder="0" class="wide-input form-control"/>
                <form:errors path="numberOfRooms" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<c:set var="sizeErrors"><form:errors path="size"/></c:set>
        <div class="form-group<c:if test="${not empty sizeErrors}"> has-error</c:if>">
            <label class="control-label" for="field-size">Apartment size (m<sup>2</sup>) *</label>
            <div class="controls">
                <form:input path="size" id="field-size" tabindex="16" maxlength="5"  class="wide-input form-control"/>
                <form:errors path="size" cssClass="help-inline" element="span"/>
            </div>
        </div>
		
		<c:set var="descriptionErrors"><form:errors path="description"/></c:set>
        <div class="form-group<c:if test="${not empty descriptionErrors}"> has-error</c:if>">
            <label class="control-label" for="field-description">Description</label>
            <div class="controls">
                <form:textarea path="description" id="field-description" tabindex="17" rows="10" class="wide-input form-control"/>
                <form:errors path="description" cssClass="help-inline" element="span"/>
            </div>
        </div>
        </fieldset>
</div>
<div class="col-sm-6">        
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
        <br/>
        <legend>Upload images</legend>
        
        <c:if test="${apForm.uploadedImages != 0}">
        	
        	<c:forEach begin="1" end="${apForm.uploadedImages}" var="val">
        		
        		<img alt="ad image" src="../ApartmentImages/${apForm.id}_${val}.jpg" class="small-image">
        		
        	</c:forEach>
        	<br/>
        </c:if>
        
        <div></div>
        <div id="image1" >
        	<input name="AdImages[0]" type="file" />
        </div>
        <div id="image2" style="display:none;">
        	<input name="AdImages[1]" type="file" />
        </div>
        <div id="image3" style="display:none;">
        	<input name="AdImages[2]" type="file" />
        </div>
        <div id="image4" style="display:none;">
        	<input name="AdImages[3]" type="file" />
        </div>
        <div id="image5" style="display:none;">
        	<input name="AdImages[4]" type="file" />
        </div>
        <div id="image6" style="display:none;">
        	<input name="AdImages[5]" type="file" />
        </div>
        <div id="image7" style="display:none;">
        	<input name="AdImages[6]" type="file" />
        </div>
        <div id="image8" style="display:none;">
        	<input name="AdImages[7]" type="file" />
        </div>
        <div id="image9" style="display:none;">
        	<input name="AdImages[8]" type="file" />
        </div>
        <div id="image10" style="display:none;">
        	<input name="AdImages[9]" type="file" />
        </div>
        <button class="btn btn-green btn-xs pull-right" id="addImages" type="button" onclick="addImage()">Add more images</button>
        
</div>
</div>
<hr/>
<div class="row">
<div class="col-sm-6"></div>        
<div class="col-sm-6 text-right">		
        <div class="form-actions">
            <button type="submit" class="btn btn-green">Submit Ad</button>
            <a href="/main" class="btn btn-grey" role="button" >Cancel</a>
        </div>
    </fieldset>
</form:form>
</div>
</div>

<script>
	var count=1;
    function addImage(){
    	count = count+1;
    	document.getElementById('image'+count).style.display='block';
    	
    }
</script>

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