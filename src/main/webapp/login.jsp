<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Login: event scheduler</title>

    <!-- Include CSS files -->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/app.css"/>">
</head>
<body>

    <div class="wrapper">
        <form class="form-signin" name='loginForm' action="<c:url value='/j_spring_security_check' />" method='post'>
            <h2 class="form-signin-header">Please login</h2>
            <input type="text" class="form-control" name="username" placeholder="Username" required="" autofocus="" />
            <input type="password" class="form-control" name="password" placeholder="Password" required="" />
            <button class="btn btn-primary btn-block">Login</button>
            <div class="form-signin-to-signup">
                <a href="<c:url value="/registration" />">Sign Up Here</a>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form><!-- .END form -->
    </div><!-- .END wrapper -->

    <!-- Include JS scripts -->
    <script src="<c:url value="/assets/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>

</body>
</html>
