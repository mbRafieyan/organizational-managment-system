<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.CategoryElementEntity" %>
<%@ page import="java.util.List" %>

<%
    List<CategoryElementEntity> roleCategoryElementList = (List<CategoryElementEntity>) request.getSession().getAttribute("roleCategoryElementList");
    System.out.println("roleCategoryElementList=> " + roleCategoryElementList.size());
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>UpdateEmployee</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/image/dotin.jpg">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Update Employee</h1>
    <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It's built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">
    <div class="card-deck mb-3">
        <div class="card mb-4 shadow-sm">
            <div class="card-header text-center">
                <h4 class="my-0 font-weight-normal">Update Employee</h4>
            </div>
            <div class="card-body">
                <form method="POST" action="<%=request.getContextPath()%>/showEmployee/saveEmployee">
                    <div class="form-group">

                        <label for="InputFirstName">First Name</label>
                        <input type="text" name="firstName" class="form-control" id="InputFirstName" placeholder="First Name"/>

                        <label for="InputLastName">Last Name</label>
                        <input type="text" name="LastName" class="form-control" id="InputLastName" placeholder="Last Name"/>

                        <label for="InputEmail">Email</label>
                        <input type="text" name="email" class="form-control" id="InputEmail" placeholder="Email address"/>

                        </br>
                        <label for="selectRole">Eployee Role</label>
                        <select class="browser-default custom-select" id="selectRole">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>

                        </br></br>
                        <label for="selectManager">Employee Manager</label>
                        <select class="browser-default custom-select" id="selectManager">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>

                    <a type="button" href="<%=request.getContextPath()%>/employee" class="btn btn-dark">Cancel</a>
                    <button type="submit" class="btn btn-primary">Submit</button>

                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
</html>
