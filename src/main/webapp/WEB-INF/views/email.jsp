<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String employeeId = request.getParameter("employeeid");
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Emial</title>
        <link rel="icon" href="<%=request.getContextPath()%>/resources/image/dotin.jpg">
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
            <h1 class="display-4">Send or Show Your Emails</h1>
            <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example.
                It's built with default Bootstrap components and utilities with little customization.</p>
        </div>

        <div class="container">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Email</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/email/viewAddEmail"
                           class="btn btn-lg btn-block btn-outline-primary">Send Email</a>
                    </div>
                </div>
            </div>
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Sent</h4>
                    </div>
                    <div class="card-body">
                        <c:url value="/email/" var="sentEmailUrl">
                            <c:param name="employeeId" value='${employeeId ? employeeId : "0"}'/>
                            <c:param name="mailBoxName" value="sent"/>
                        </c:url>
                        <a href="${sentEmailUrl}" class="btn btn-lg btn-block btn-outline-primary">Sent Emails</a>
                    </div>
                </div>
            </div>
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Inbox</h4>
                    </div>
                    <div class="card-body">
                        <c:url value="/email/" var="inboxEmailUrl">
                            <c:param name="employeeId" value='${employeeId ? employeeId : "0"}'/>
                            <c:param name="mailBoxName" value="inbox"/>
                        </c:url>
                        <a href="${inboxEmailUrl}" class="btn btn-lg btn-block btn-outline-primary">Inbox Emails</a>
                    </div>
                </div>
            </div>
        <%@include file="footer.jsp" %>
</html>