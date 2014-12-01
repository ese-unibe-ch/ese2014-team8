<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">

    <title>RentR</title>

    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <script src="https://login.persona.org/include.js"></script>
	<link rel="stylesheet" href="../../css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../../css/main.css" media="all"/>
    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript"  src="../../js/bootstrap.min.js"></script>
    
    

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!--<style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>-->
</head>
<body> 

	<img class="top-image" src="../../img/cityscape_large.jpg"/>


<div class="log-in">
<c:choose>
<c:when test="${user.email != null}">
You're logged in as ${user.email}.<input type="button" id="signout" value="signout" />
</c:when>
<c:otherwise>
<img id="signin" alt="Sign In" src="../../img/plain_sign_in_black.png" />
</c:otherwise>
</c:choose>

	<script type="text/javascript">
	<!--
	var currentUser = $.cookie('current_user') || null;

	$(document).ready(function() {

<c:choose>
<c:when test="${user.email != null}">
		document.getElementById('signout').onclick = function() {
			navigator.id.logout();
		}
		</c:when>
		<c:otherwise>
		document.getElementById('signin').onclick = function() {
			navigator.id.request();
		}
		</c:otherwise>
		</c:choose>

		navigator.id.watch( {
			loggedInUser: currentUser,
			onlogin: function(assertion) {
				$.ajax({
				      type: 'POST',
				      url: '/auth/login',
				      data: {assertion: assertion},
				      success: function(res, status, xhr) {
				    	  // alert(status + ' successful login');
				    	  <c:if test="${user.email == null}">
				    	  //window.location.reload();
				    	  window.location = '/main';
				    	  </c:if>
				    	  var result = $.parseJSON(res);
				    	  currentUser = result.name;
				    	  $.cookie('current_user', currentUser, {
				    		 expires: 7,
				    		 path: '/',
				    		 secure: true
				    	  });

				   	  },
				      error: function(xhr, status, err) {
				    	$.ajax({
				    		type: 'POST',
				    		url: '/auth/login',
				    		data: {assertion: assertion},
				    		success: function(res, status, xhr) { window.location.reload(); },
				    		error: function(xhr, status, err) {
						        navigator.id.logout();
						        alert("Login failure: " + status + " - " +err);
				    		}
				    	})
				      }
				    });
			},
			onlogout: function() {
				$.ajax({
				      type: 'POST',
				      url: '/auth/logout', // This is a URL on your website.
				      success: function(res, status, xhr) { window.location.reload(); },
				      error: function(xhr, status, err) { alert("Logout failure: " + err); }
				    });
			}
		} );

	});
	-->
</script>
</div>
</div> 

<div class="container">


<div class="jumbotron text-center">
<h1>Welcome to RENTR!</h1>
<h2>Sign in and get access to <br/> the most detailed apartment and shared apartment ads, <br/>roommate profiles, and much more...</h2>

<c:import url="template/footer.jsp" />
