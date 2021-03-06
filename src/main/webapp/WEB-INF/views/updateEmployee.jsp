<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>UpdateEmployee</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/images/dotin.jpg">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">${subject} Employee</h1>
    <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It's built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">
    <div class="card-deck mb-3">
        <div class="card mb-4 shadow-sm">
            <div class="card-header text-center">
                <h4 class="my-0 font-weight-normal">${subject}  Employee</h4>
            </div>
            <div class="card-body">
                <c:url var="updateEmployee" value="/viewEmployee/updateEmployee" ></c:url>
                <form:form method="POST" name="employeeForm" action="${updateEmployee}" modelAttribute="employeeEntity">
                    <div class="form-group">

                        <form:input type="hidden" path="id" />

                        <label for="inputFirstName">First Name</label>
                        <form:input type="text" path="firstName" class="form-control" id="inputFirstName" placeholder="First Name"/>
                        <form:errors path="firstName" cssClass="validation-error"></form:errors>

                        </br>
                        <label for="inputLastName">Last Name</label>
                        <form:input type="text" path="lastName" class="form-control" id="inputLastName" placeholder="Last Name"/>
                        <form:errors path="lastName" cssClass="validation-error"></form:errors>

                        </br>
                        <label for="inputEmail">Email</label>
                        <form:input type="text" path="emailAddress" class="form-control" id="inputEmail" placeholder="Email address"/>
                        <form:errors path="emailAddress" cssClass="validation-error"></form:errors>

                        </br>
                        <label for="selectRole">Eployee Role</label>
                        <form:select path="employeeRole" id="selectRole" cssClass="browser-default custom-select">
                            <form:option value="" label="select role employee"></form:option>
                            <form:options items="${roleCategoryElementList}" itemValue="id" itemLabel="name"></form:options>
                        </form:select>
                        <form:errors path="employeeRole" cssClass="validation-error"></form:errors>

                        </br></br>
                        <label for="selectManager">Employee Manager</label>
                        <select name = "emManager" id="selectManager" class="browser-default custom-select">
                            <option value="0"><c:out value="Select Employee Managment"/></option>
                            <c:forEach var="manager" items="${managerEmployeeEntityMap}">
                                <c:choose>
                                    <c:when test="${employeeEntity != null && employeeEntity.employeeManager.id == manager.key}">
                                        <option selected value="${manager.key.toString()}"><c:out value="${manager.value}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option  value="${manager.key.toString()}"><c:out value="${manager.value}"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                    <a type="button" href="<%=request.getContextPath()%>/viewEmployee" class="btn btn-dark">Cancel</a>
                    <form:button type="submit" class="btn btn-primary">Submit</form:button>
                </form:form>
            </div>
        </div>
    </div>

<%@include file="footer.jsp" %>

<script>
    $(function() {
        $("form[name='employeeForm']").validate({

            rules: {
                firstName: "required",
                lastName: "required",
                emailAddress: {
                    required: true,
                    email: true
                },
                employeeRole: "required"
            },
            messages: {
                firstName: "Please enter firstName",
                lastName: "Please enter lastName",
                emailAddress: {
                    required: "Please enter email address",
                    email: "Please enter a valid email address"
                },
                employeeRole: "Please select employee role"
            },
        });
    });
</script>

</html>
