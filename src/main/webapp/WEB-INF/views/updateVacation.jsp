<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>UpdateVacation</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/images/dotin.jpg">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/jquery.md.bootstrap.datetimepicker.style.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">${subject} Vacation</h1>
    <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example.
        It's built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">
    <div class="card-deck mb-3">
        <div class="card mb-4 shadow-sm">
            <div class="card-header text-center">
                <h4 class="my-0 font-weight-normal">${subject} Vacation</h4>
            </div>
            <div class="card-body">
                <c:url var="updateVacation" value="/viewVacation/addVacation"></c:url>
                <form:form method="POST" name="vacationForm" action="${updateVacation}" modelAttribute="vacationsEntity">

                    <form:input type="hidden" path="id"/>

                        <label for="inputVacationType" class="col-md-2 control-label">Vacation Type</label>
                        <c:forEach var="type" items="${vacationTypeList}">
                            <div class="form-check col-md-2" id="inputVacationType">
                                <input type="radio" value="${type.getId().toString()}" class="form-check-input"
                                       id="${type.getId().toString()}" name="vacationType" checked>
                                <label class="form-check-label col-md-8" for="${type.getId().toString()}"><c:out value="${type.getName()}"/></label>
                            </div>
                        </c:forEach>
                        </br>

                        <label for="selectEmployee" class="col-md-2 control-label">Select Employee</label>
                        <div class="input-group col-md-5">
                            <form:select path="employee" id="selectEmployee" cssClass="browser-default custom-select vacation-jquery-validation">
                                <form:option value="" label="select employee"></form:option>
                                <c:forEach var="emp" items="${employeeEntities}">
                                    <form:option value="${emp.id.toString()}"><c:out value="${emp.firstName}${\" \"}${emp.lastName}"/></form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <form:errors path="employee" cssClass="validation-error vacation-error"></form:errors>

                        </br>
                        <label for="inputDate1" class="col-md-2 control-label">Start Date</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text cursor-pointer" id="date1">
                                        <img class="mb-2" src="<%=request.getContextPath()%>/resources/images/calendar.png" alt="" width="24"
                                             height="24">
                                    </span>
                                </div>
                                <form:input path="vacationStart" type="text" id="inputDate1" class="form-control vacation-jquery-validation" placeholder="From Date" aria-label="date4"
                                            aria-describedby="date4"></form:input>
                            </div>
                        </div>
                        <form:errors path="vacationStart" cssClass="validation-error vacation-error"></form:errors>
                        <input type="hidden" id="dtp_input1" value=""/>

                        </br>
                        <label for="inputDate2" class="col-md-2 control-label">End Date</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text cursor-pointer" id="date2">
                                        <img class="mb-2" src="<%=request.getContextPath()%>/resources/images/calendar.png" alt="" width="24"
                                             height="24">
                                    </span>
                                </div>
                                <form:input path="vacationEnd" type="text" id="inputDate2" class="form-control vacation-jquery-validation" placeholder="End Date" aria-label="date4"
                                            aria-describedby="date4"></form:input>
                            </div>
                        </div>
                        <form:errors path="vacationEnd" cssClass="validation-error vacation-error"></form:errors>
                        <input type="hidden" id="dtp_input2" value=""/><br/>

                    <a type="button" href="<%=request.getContextPath()%>/viewVacation" class="btn btn-dark btn-cancel-vacation">Cancel</a>
                    <form:button type="submit" class="btn btn-primary">Submit</form:button>
                </form:form>
            </div>
        </div>
    </div>
    <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
            <div class="col-12 col-md">
                <img class="mb-2" src="<%=request.getContextPath()%>/resources/images/dotin.jpg" alt="" width="24"
                     height="24">
                <small class="d-block mb-3 text-muted">&copy; 2019-2020</small>
            </div>

            <div class="col-6 col-md">
                <h5>Employee</h5>
                <ul class="list-unstyled text-small">
                    <li><a class="text-muted" href="<%=request.getContextPath()%>/viewEmployee">Add Employee</a></li>
                </ul>
            </div>

            <div class="col-6 col-md">
                <h5>Email</h5>
                <ul class="list-unstyled text-small">
                    <li><a class="text-muted" href="<%=request.getContextPath()%>/email">Send Email</a></li>
                </ul>
            </div>

            <div class="col-6 col-md">
                <h5>Vacations</h5>
                <ul class="list-unstyled text-small">
                    <li><a class="text-muted" href="<%=request.getContextPath()%>/vacations">Request Vacation</a></li>
                </ul>
            </div>

        </div>
    </footer>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.5.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/holder.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.md.bootstrap.datetimepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>

<script>
    Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
    });

    $('#date1').MdPersianDateTimePicker({
        targetTextSelector: '#inputDate1',
        fromDate: true,
        enableTimePicker: true,
        groupId: 'rangeSelector1',
        dateFormat: 'yyyy-MM-dd HH:mm:ss',
        textFormat: 'yyyy-MM-dd HH:mm:ss',
    });

    $('#date2').MdPersianDateTimePicker({
        targetTextSelector: '#inputDate2',
        fromDate: true,
        enableTimePicker: true,
        groupId: 'rangeSelector1',
        dateFormat: 'yyyy-MM-dd HH:mm:ss',
        textFormat: 'yyyy-MM-dd HH:mm:ss',
    });

    $(function() {
        $("form[name='vacationForm']").validate({

            rules: {
                employee: "required",
                vacationStart: "required",
                vacationEnd: "required"
            },
            messages: {
                employee: "Please select employee",
                vacationStart: "Please enter start vacation date",
                vacationEnd: "Please enter end vacation date"
            },
        });
    });

</script>

</body>
</html>
