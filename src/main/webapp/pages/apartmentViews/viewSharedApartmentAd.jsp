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