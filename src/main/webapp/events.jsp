<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Event points: event scheduler</title>

    <!-- Include CSS files -->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-datepicker3.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/app.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/timeline.css" />">
</head>
<body>

<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-8" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/events" />">Event scheduler</a>
            </div><!-- .END navbar header -->

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-8">
                <ul class="nav navbar-nav navbar-left">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="dropdown">
                            <a href="<c:url value="/users" />" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                Users <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li role="presentation" class="dropdown-header">User management:</li>
                                <li><a href="<c:url value="/users" />"><i class="fa fa-user"></i> Users</a></li>
                                <li role="separator" class="divider"></li>
                                <li role="presentation" class="dropdown-header">Authority management:</li>
                                <li><a href="<c:url value="/authorities" />"><i class="fa fa-lock"></i> Authorities</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <li class="dropdown">
                        <a href="events.html" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            Events <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li role="presentation" class="dropdown-header">Event management:</li>
                            <li><a href="<c:url value="/events" />"><i class="fa fa-calendar"></i> Events</a></li>
                            <li><a href="<c:url value="/event_points" />"><i class="fa fa-circle-o"></i> Event points</a></li>
                            <li role="separator" class="divider"></li>
                            <li role="presentation" class="dropdown-header">Simple planner:</li>
                            <li><a href="<c:url value="/scheduler" />"><i class="fa fa-paper-plane"></i> Scheduler</a></li>
                        </ul>
                    </li>
                </ul><!-- .END left navbar -->

                <ul class="nav navbar-nav navbar-right">
                    <c:url value="/j_spring_security_logout" var="logoutUrl" />
                    <form action="${logoutUrl}" method="post" id="logoutForm">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form>
                    <li><a href="javascript:formSubmit()">Exit</a></li>
                    <!-- <button type="button" class="btn btn-default navbar-btn">Sign out</button> -->
                </ul><!-- .END right navbar -->
            </div><!-- .END navbar collapse-->
        </div><!-- .END container -->
    </nav><!-- .END navbar -->

    <!-- div class="container">
        <div class="alert alert-success" role="alert">
            Event point successfully deleted
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        </div>
        <div class="alert alert-info" role="alert">
            Event point successfully added
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        </div>
    </div> --><!-- .END container block -->

    <div class="container">
        <ol class="breadcrumb">
            <li><a href="<c:url value="/events" />">Home</a></li>
            <li class="active">Events</li>
        </ol><!-- .END breadcrumb -->

        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default panel-table">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="line-height: 30px;">Events</h3>
                    </div><!-- .END panel header -->

                    <div class="panel-body">
                        <div class="col-md-4">
                            <div class="container-fluid">
                                <c:url var="event_add"  value="/events/add" />
                                <form class="form-events" modelAttribute="event" method="get" action="${event_add}">
                                    <div class="form-group">
                                        <label for="eventPointId">Event points:</label>
                                        <select name="eventPointId" class="form-control">
                                            <c:forEach items="${eventPoint}" var="eventPoint" >
                                                <option value="<c:out value="${eventPoint.id}" />"><c:out value="${eventPoint.eventPointName}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- <div class="form-group">
                                        <label for="exampleInputName2">Weekday:</label>
                                        <select class="form-control">
                                          <option>1: Monday</option>
                                          <option>2: Tuesday</option>
                                          <option>3: Wednesday</option>
                                          <option>4: Thursday</option>
                                          <option>5: Friday</option>
                                          <option>6: Saturday</option>
                                          <option>7: Sunday</option>
                                        </select>
                                    </div> -->
                                    <div class="form-group">
                                        <label for="description">Event description:</label>
                                        <input type="text" name="description" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="start">Start date:</label>
                                        <div class="input-group date" data-provide="datepicker">
                                            <input type="text" name="start" class="form-control">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="end">End date:</label>
                                        <div class="input-group date" data-provide="datepicker">
                                            <input type="text" name="end" class="form-control">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-default"><i class="fa fa-paper-plane"></i> Send event</button>
                                </form>
                            </div>
                        </div><!-- END col-md-4 -->

                        <div class="col-md-8">
                            <div class="timeline">
                                <div class="line text-muted"></div>
                                <c:forEach items="${event}" var="event" >
                                    <form>
                                        <article class="panel panel-default">
                                            <div class="panel-heading icon">
                                                <i class="glyphicon glyphicon-plus"></i>
                                            </div>

                                            <div class="panel-heading">
                                                <h2 class="panel-title"><c:out value="${event.eventPoint}" /></h2>
                                            </div>

                                            <div class="panel-body">
                                                <h4><c:out value="${event.description}" /></h4>
                                                <div>Start date: <c:out value="${event.startDate}" /></div>
                                                <div>End date: <c:out value="${event.endDate}" /></div>
                                            </div>

                                            <div class="panel-footer">
                                                <a href="<c:url value="/events/delete/${event.id}" />" type="submit" class="btn btn-default"><em class="fa fa-trash"></em></a>
                                            </div>
                                        </article>
                                    </form>
                                </c:forEach>
                                <%--<div class="separator text-muted">--%>
                                    <%--<time class=""></time>--%>
                                <%--</div>--%>
                            </div>
                        </div><!-- END col-md-8 -->
                    </div><!-- .END panel body -->
                </div><!-- .END panel -->
            </div>
        </div><!-- .END row block -->
    </div><!-- .END container block -->
</sec:authorize>

    <!-- Include JS scripts -->
    <script src="<c:url value="/assets/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/assets/js/bootstrap-datepicker.min.js" />"></script>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
</body>
</html>