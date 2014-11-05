<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">

    <title>TestApp</title>

    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <link rel="stylesheet" type="text/css" href="../../css/main.css" media="all"/>
    <script type="text/javascript"  src="//dn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://login.persona.org/include.js"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            

        </div>
    </div>
</div>

<c:choose>
<c:when test="${user.email != null}">
You're logged in as ${user.email}. <input type="button" id="signout" value="signout" />
</c:when>
<c:otherwise>
<img id="signin" alt="Sign In" src="https://mdn.mozillademos.org/files/3969/plain_sign_in_blue.png" />
</c:otherwise>
</c:choose>
<!--<img id="signin" alt="Sign In" src="https://mdn.mozillademos.org/files/3969/plain_sign_in_blue.png" />
	${username}
	<input type="button" id="signout" value="signout" />-->
	<script type="text/javascript">
	<!--
	var currentUser = $.cookie('current_user') || null;

	$(document).ready(function() {

		document.getElementById('signin').onclick = function() {
			navigator.id.request();
		}

		document.getElementById('signout').onclick = function() {
			navigator.id.logout();
		}

		navigator.id.watch( {
			loggedInUser: currentUser,
			onlogin: function(assertion) {
				$.ajax({
				      type: 'POST',
				      url: '/auth/login',
				      data: {assertion: assertion},
				      success: function(res, status, xhr) {
				    	  // alert(status + ' successful login');
				    	  var result = $.parseJSON(res);
				    	  currentUser = result.name;
				    	  $.cookie('current_user', currentUser, {
				    		 expires: 7,
				    		 path: '/',
				    		 secure: true
				    	  });
				    	  window.location.reload();
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

<div class="container">