<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>SearchForm</h1>


<form:form method="post" modelAttribute="searchForm" action="search" id="searchForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>This is a Search Form</legend>

	

        
        <c:set var="appartmentErrors"><form:errors path="appartment"/></c:set>
        <div class="control-group<c:if test="${not empty appartmentErrors}"> error</c:if>">
            <label class="control-label" for="field-appartment">Appartment</label>
            <div class="controls">
                <form:select path="appartment" id="field-appartment" tabindex="4">
                    <option value="-1">appartment</option>
                    <option value="-2">shared appartment</option>
<%--                     <c:forEach var="t" items="${appartments}">
                        <option value="${t.id}">${t.appartment}</option>
                    </c:forEach> --%>
                </form:select>
                <form:errors path="appartment" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="zipCodeErrors"><form:errors path="zipCode"/></c:set>
        <div class="control-group<c:if test="${not empty zipCodeeErrors}"> error</c:if>">
            <label class="control-label" for="field-zipCode">Zip-Code</label>
            <div class="controls">
                <form:input path="zipCode" id="field-zipCode" tabindex="3" maxlength="35" placeholder="Zip-Code"/>
                <form:errors path="zipCode" cssClass="help-inline" element="span"/>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Search</button>
            <button type="button" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>




	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
