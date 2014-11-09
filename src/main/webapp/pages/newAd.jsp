<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<c:if test="${editType == Null}">
<h1>Advertise your apartment here!</h1>
<select id="category" size="1">
      <option value="1">Apartment</option>
      <option value="2">Shared Apartment</option>
</select>
</c:if>
<c:if test="${editType == 'Apartment'}">
	<h1>Edit your Apartment Ad</h1>
<select id="category" size="1">
      <option value="1">Apartment</option>
</select>
</c:if>
<c:if test="${editType == 'Shared Apartment'}">
	<h1>Edit your Shared Apartment Ad</h1>
<select id="category" size="1">
      <option value="2">Shared Apartment</option>
</select>
	<%-- <script>
		document.getElementById('category').options[1]=new Option("Shared Apartment", "2", false, true)
	</script> --%>
</c:if>

<div id="hiddenVariable">
	<input type="hidden" value="Myname" name="nom" />
</div> 


<c:import url="adForms/apartmentForm.jsp" />

<c:import url="adForms/sharedApartmentForm.jsp" />


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

<%--  <script>
	document.getElementById('category').options[1]=new Option("FakeShApartment", "2", false, true)
</script> --%>

<script>
if (document.getElementById('category').value == 1 )
    {document.getElementById('apartmentForm').style.display = 'block';
	document.getElementById('sharedApartmentForm').style.display='none';
	}
else
    {document.getElementById('apartmentForm').style.display = 'none';
	document.getElementById('sharedApartmentForm').style.display='block';
	}
	
    document.getElementById('category').onchange = function() {
    if (document.getElementById('category').value == 1 )
        {document.getElementById('apartmentForm').style.display = 'block';
		document.getElementById('sharedApartmentForm').style.display='none';
		}
    else
        {document.getElementById('apartmentForm').style.display = 'none';
		document.getElementById('sharedApartmentForm').style.display='block';
		}
}
</script>
	
<c:import url="template/footer.jsp" />
