<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<c:if test="${category == 'Apartment'}">
	<h1>Edit your Apartment Ad</h1>
	<c:import url="adForms/apartmentForm.jsp" />
</c:if>

<c:if test="${category == 'Shared Apartment'}">
	<h1>Edit your Shared Apartment Ad</h1>
	<c:import url="adForms/sharedApartmentForm.jsp" />
</c:if>


<c:if test="${page_error != null }">
    <div class="alert alert-error">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <h4>Error!</h4>
            ${page_error}
    </div>
</c:if>

<script>
	
	var elem1 = document.getElementById('moveInJS'),
		checkBox1 = document.getElementById('field-fixedMoveIn');
	
	function showElement1(){
	    elem1.style.display = checkBox1.checked ?  'block' : 'none';
	};
	
	checkBox1.onclick = function(){showElement1()};
	window.onload = function(){showElement1()};

	
	
	var elem2 = document.getElementById('moveOutJS<c:if test="${apForm.fixedMoveOut}">_show</c:if>'),
		checkBox2 = document.getElementById('field-fixedMoveOut');
	checkBox2.onclick = function(){
	    elem2.style.display = this.checked ?  'block' : 'none';
	};
</script>

	
<c:import url="template/footer.jsp" />
