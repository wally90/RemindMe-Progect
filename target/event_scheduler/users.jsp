<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Users: event scheduler</title>

    <!-- Include CSS files -->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/app.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/table.css" />">
</head>
<body>

<sec:authorize access="hasRole('ROLE_ADMIN')">
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
            User successfully deleted
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        </div>
        <div class="alert alert-info" role="alert">
            User successfully added
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        </div>
    </div> --><!-- .END container block -->

    <div class="container">
        <ol class="breadcrumb">
            <li><a href="<c:url value="/events" />">Home</a></li>
            <li class="active">Users</li>
        </ol><!-- .END breadcrumb -->

        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default panel-table">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col col-xs-6">
                                <h3 class="panel-title">List of users</h3>
                            </div>
                            <!-- <div class="col col-xs-6 text-right">
                              <button type="button" class="btn btn-sm btn-primary btn-create">Create New</button>
                            </div> -->
                        </div>
                    </div><!-- .END panel heading -->

                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th><em class="fa fa-cog"></em></th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Enabled</th>
                                <th>First name</th>
                                <th>Second name</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <c:url var="user_add"  value="/users/add" />
                                <form modelAttribute="user" method="GET" action="${user_add}">
                                    <td>
                                        <button type="submit" class="btn btn-primary" value="Submit"><em class="fa fa-plus"></em></button>
                                    </td>
                                    <td>
                                        <input type="text" name="username"  placeholder="Username" class="form-control"/>
                                    </td>
                                    <td>
                                        <input type="password" name="password" placeholder="Password" class="form-control"/>
                                    </td>
                                    <td>
                                        <input name="enabled" value="1" placeholder="Enabled" class="form-control" />
                                    </td>
                                    <td>
                                        <input type="text" name="firstName" placeholder="First name" class="form-control"/>
                                    </td>
                                    <td>
                                        <input type="text" name="secondName" placeholder="Second name" class="form-control"/>
                                    </td>
                                </form>
                            </tr>
                            <c:forEach items="${user}" var="user" >
                                <tr>
                                    <td align="center">
                                        <a href="<c:url value="/users/delete/${user.username}" />" type="submit" class="btn btn-danger"><em class="fa fa-trash"></em></a>
                                    </td>
                                    <td><c:out value="${user.username}" /></td>
                                    <td><c:out value="${user.password}" /></td>
                                    <td><c:out value="${user.enabled}" /></td>
                                    <td><c:out value="${user.firstName}" /></td>
                                    <td><c:out value="${user.secondName}" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- .END panel body -->

                    <div class="panel-footer">
                        <div class="row">
                            <!-- <div class="col col-xs-4">Page 1 of 5</div>
                              <div class="col col-xs-8">
                                <ul class="pagination hidden-xs pull-right">
                                  <li><a href="#">1</a></li>
                                  <li><a href="#">2</a></li>
                                  <li><a href="#">3</a></li>
                                  <li><a href="#">4</a></li>
                                  <li><a href="#">5</a></li>
                                </ul>
                                <ul class="pagination visible-xs pull-right">
                                    <li><a href="#">«</a></li>
                                    <li><a href="#">»</a></li>
                                </ul>
                              </div> -->
                        </div>
                    </div><!-- .END panel footer -->
                </div>
            </div>
        </div><!-- .END row block -->
    </div><!-- .END container block -->
</sec:authorize>

    <!-- Include JS scripts -->
    <script src="<c:url value="/assets/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
</body>
</html>
