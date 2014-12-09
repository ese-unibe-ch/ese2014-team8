<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="post" modelAttribute="searchForm" action="search" id="searchForm"  autocomplete="off">
    <fieldset >
        <c:set var="categoryErrors"><form:errors path="category"/></c:set>
        <div class="control-group<c:if test="${not empty categoryErrors}"> error</c:if> float-left">
            <label class="control-label" for="field-category">Category</label>
            <div class="controls">
                <form:select path="category" items = "${searchForm.categories}" id="field-category" tabindex="4">
                </form:select>
                <form:errors path="category" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <c:set var="zipCodeErrors"><form:errors path="zipCode"/></c:set>
        <div class="control-group<c:if test="${not empty zipCodeErrors}"> error</c:if> float-left">
            <label class="control-label" for="field-zipCode">Zip-Code</label>
            <div class="controls">
                <form:input path="zipCode" id="field-zipCode" tabindex="3" maxlength="35" placeholder="Zip-Code"/>
                <form:errors path="zipCode" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <c:set var="cityErrors"><form:errors path="city"/></c:set>
                <div class="control-group<c:if test="${not empty cityErrors}"> error</c:if> float-left">
                    <label class="control-label" for="field-city">City</label>
                    <div class="controls">
                        <form:input path="city" id="field-city" tabindex="3" maxlength="35" placeholder="City"/>
                        <form:errors path="city" cssClass="help-inline" element="span"/>
                    </div>
                </div>

                <c:set var="minPriceErrors"><form:errors path="minPrice"/></c:set>
                        <div class="control-group<c:if test="${not empty minPriceErrors}"> error</c:if> float-left">
                            <label class="control-label" for="field-minPrice">Minimum price:</label>
                            <div class="controls">
                                <form:input path="minPrice" id="field-minPrice" tabindex="3" maxlength="35" placeholder="Minimum price"/>
                                <form:errors path="minPrice" cssClass="help-inline" element="span"/>
                            </div>
                        </div>

                        <c:set var="maxPriceErrors"><form:errors path="maxPrice"/></c:set>
                                                <div class="control-group<c:if test="${not empty maxPriceErrors}"> error</c:if> float-left">
                                                    <label class="control-label" for="field-maxPrice">Maximal price:</label>
                                                    <div class="controls">
                                                        <form:input path="maxPrice" id="field-maxPrice" tabindex="3" maxlength="35" placeholder="Maximal price"/>
                                                        <form:errors path="maxPrice" cssClass="help-inline" element="span"/>
                                                    </div>
                                                </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-green">Search</button>

        </div>
    </fieldset>
</form:form>
</div>



	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>
<script>
	document.getElementById('search').style.color = '#ACCB12'
</script>