<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="template/header.jsp" />

<div class="row">
<div class="col-sm-3">
<div class="large">
<label class="control-label" for="field-category">Category</label><br/>
<select id="category" size="1">
      <option value="1">Apartment</option>
      <option value="2" selected >Shared Apartment</option>
</select>
</div>
</div>
<c:import url="adForms/sharedApartmentForm.jsp" />
<%-- <c:import url="roomMateTable.jsp" /> --%>

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

<script type="text/javascript">
function switchForm(){
	window.location = "/newAd";
}
document.getElementById('category').onchange = function(){switchForm()};

</script>
	
<c:import url="template/footer.jsp" />
