<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>${apartmentForm.title}</h2>
<div>
	${apartmentForm.street} ${apartmentForm.number}<br/>
	${apartmentForm.zipCode} ${apartmentForm.city}
</div>
<div>
	<iframe
		width="400"
		height="400"
		frameborder="0" style="border:0"
		src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAg7gQ_6H5xWUXFVrxyUpulXzs3flqGfcA
			&q=${apartmentForm.street}+${apartmentForm.number},${apartmentForm.zipCode}+${apartmentForm.city},Switzerland">
	</iframe>
</div>
<div>
	<c:if test="${apartmentForm.fixedMoveIn==false}"> There is no fixed move-in date.<br/> </c:if>
	<c:if test="${apartmentForm.fixedMoveIn==true}"> Move-in date: <fmt:formatDate pattern="dd/MM/yyyy" value="${apartmentForm.moveIn}" /> <br/></c:if>
	<c:if test="${apartmentForm.fixedMoveOut==false}"> There is no fixed move-out date. <br/></c:if>
	<c:if test="${apartmentForm.fixedMoveOut==true}"> Move-out date: <fmt:formatDate type="date" dateStyle="short" value="${apartmentForm.moveOut}" /><br/></c:if>
	Price: ${apartmentForm.price} chf<br/>
	Number of rooms: ${apartmentForm.numberOfRooms}<br/>
	Size: ${apartmentForm.size}<br/>
</div>
<div>
	Description: <br/>
	${apartmentForm.description}
</div>