<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Registration: event scheduler</title>

    <!-- Include CSS files -->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/app.css" />">
</head>
<body>

    <div class="wrapper">
        <c:url var="registration_user"  value="/registration/add" />
        <form class="form-signup" modelAttribute="user" method="get" action="${registration_user}">
            <h2 class="form-signup-header">Please registration</h2>
            <input type="text" class="form-control" name="firstName" placeholder="First name" required="" autofocus="" />
            <input type="text" class="form-control no-border-radius" name="secondName" placeholder="Second name" required="" autofocus="" />
            <input type="text" class="form-control no-border-radius" name="username" placeholder="Username" required="" autofocus="" />
            <input type="password" class="form-control" name="password" placeholder="Password" required="" />
            <input type="hidden" name="enabled" value="1" />
            <button type="submit" class="btn btn-primary btn-block">Registration</button>
            <div class="form-signin-to-signup">
                <a href="<c:url value="/login" />">Sign In Here</a>
            </div>
        </form><!-- .END form -->
    </div><!-- .END wrapper -->

    <!-- Include JS scripts -->
    <script src="<c:url value="/assets/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>

</body>
</html>