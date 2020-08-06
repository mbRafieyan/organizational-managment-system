<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Welcome</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/image/dotin.jpg">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Organizational Managment System</h1>
    <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example.
        It's built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">
    <div class="card-deck mb-3 text-center">

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Employee</h4>
            </div>
            <div class="card-body">
                <a href="<%=request.getContextPath()%>/viewEmployee" class="btn btn-lg btn-block btn-outline-primary">Add Employee</a>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Email</h4>
            </div>
            <div class="card-body">
                <a href="<%=request.getContextPath()%>/email/viewEmail" class="btn btn-lg btn-block btn-outline-primary">Send
                    Email</a>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Vacation</h4>
            </div>
            <div class="card-body">
                <a href="<%=request.getContextPath()%>/viewVacation" class="btn btn-lg btn-block btn-outline-primary">Request
                    Vacation</a>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</html>
