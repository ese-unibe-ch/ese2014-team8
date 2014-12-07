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
	<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../../css/main.css" media="all"/>
    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript"  src="../../js/bootstrap.min.js"></script>
    <BASE href="https://localhost:8443">
    

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
<div class="fluid-container">
	<img class="top-image" src="../../img/cityscape_banner.jpg"/>
	<a class="logo" href="/main">
		<img alt="RentR" src="../../img/logo.png">
	</a>
	
	
	<nav class="navbar navbar-inverse " role="navigation">
		
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${user.isAdmin == true}">
			<li class="text-center text-nowrap">
				<a id="admin" href="/admin">ADMIN PANEL</a>
			</li>
			</c:if>
			<li class="text-center text-nowrap">
				<a id="search" href="/search">SEARCH</a>
			</li>
			<li class="text-center text-nowrap">
				<a id="ad" href="/newAd">CREATE AD</a>
			</li>
			<li class="text-center text-nowrap dropdown">
				<a id="profile" href="/profile" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
					MY ACCOUNT<span class="caret"></span></a>
				<ul class="dropdown-menu inverse-dropdown" role="menu">
					<li><a href="/profile">My Profile</a></li>
					<li class="divider"></li>
            		<li><a href="/placedAds">Placed Ads</a></li>
            		<li><a href="/bookmarkedAds">Bookmarked Ads</a></li>
					<li><a href="/upcomingVisits">Upcoming Visits</a></li>
            		<li class="divider"></li>
            		<li><a href="/searchAlerts">Saved Searches</a></li>
            		<li class="divider"></li>
            		<li><a href="/messages">Messages</a></li>
			</li>
		</ul>
	</nav>

<div class="log-in">
<c:choose>
<c:when test="${user.email != null}">
<img id="signout" alt="Sign Out" class="logout" src="../../img/logout2.png" />
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

<div class="container container-body">