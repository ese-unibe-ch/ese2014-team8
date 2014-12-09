<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<nav>
	<ul class="pager">
		
		<li class="disabled"><span><b>1. Create Ad </b><span class="glyphicon glyphicon-user" aria-hidden="true"></span> </span></li>
		<li class="disabled"><span><b>2. View Ad</b></span></li>
		<li class="disabled"><span><b>3. Add Visiting Times</b></span></li>
	</ul>
</nav>

<c:import url="adForms/apartmentForm.jsp" />

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
	checkBox1.onclick = function(){
	    elem1.style.display = this.checked ?  'block' : 'none';
	};
	
	var elem2 = document.getElementById('moveOutJS'),
		checkBox2 = document.getElementById('field-fixedMoveOut');
	checkBox2.onclick = function(){
	    elem2.style.display = this.checked ?  'block' : 'none';
	};
</script>


<script>
	document.getElementById('ad').style.color = '#ACCB12'
</script>
	
<c:import url="template/footer.jsp" />
