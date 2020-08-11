<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Vacation</title>
        <link rel="icon" href="<%=request.getContextPath()%>/resources/images/dotin.jpg">
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <c:if test="${successMassage != null}">
        <div class="alert alert-success text-center" role="alert">${successMassage}</div>
        </c:if>

        <c:if test="${failedMassage != null}">
        <div class="alert alert alert-danger text-center" role="alert">${failedMassage}</div>
        </c:if>

        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
            <h1 class="display-4">Add, Delete Or Update Your Vacation</h1>
            <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example.
                It's built with default Bootstrap components and utilities with little customization.</p>
        </div>

        <div class="container">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Vacation</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/viewVacation/viewUpdateVacations"
                           class="btn btn-lg btn-block btn-outline-primary">Request Vacation</a>
                    </div>
                </div>
            </div>

            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Show Employees</h4>
                    </div>

                    <c:set value="${vacationsEntityList}" var="vacationsPageList"/>
                    <table class="table table-hover" align="center">

                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Start Date</th>
                            <th scope="col">End Date</th>
                            <th scope="col">Type</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${vacationsPageList.pageList}" var="vacation" begin="0" step="1"
                                   varStatus="rowNumber">
                            <tr>
                                <td align="center">${rowNumber.index+1}</td>
                                <td align="center">${vacation.employeeEntity.firstName}${" "}${vacation.employeeEntity.lastName}</td>

                                <jsp:useBean id="convertor" class="com.util.Convertor"/>
                                <td align="center">${convertor.gregorianDateToPersian(vacation.vacationStart)}</td>
                                <td align="center">${convertor.gregorianDateToPersian(vacation.vacationEnd)}</td>
                                <td align="center">${vacation.vacationTypeCee.name}</td>
                                <td align="center">${vacation.vacationStatusCee.name}</td>
                                <td align="center">
                                    <div class="dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            Action
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">

                                            <c:forEach items="${vacationStatusMap}" var="vacationStatus">
                                                <c:if test = "${vacationStatus.value != \"indeterminate\"}">
                                                    <c:url value="/viewVacation/updateVacation/${vacation.id}/${vacationStatus.key.toString()}" var="url"/>
                                                    <a href='<c:out value="${url}" />' class="dropdown-item" type="button"><c:out value="${vacationStatus.value}"/></a>
                                                </c:if>
                                            </c:forEach>

                                            <c:url value="/viewVacation/deleteVacation/${vacation.id}" var="deleteUrl"/>
                                            <a class="dropdown-item" href="${deleteUrl}" type="button">Delete</a>

                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${vacationsPageList.firstPage}">
                                    <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/viewVacation/prev" var="url"/>
                                    <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>Previous</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="1" end="${vacationsPageList.pageCount}" step="1" varStatus="tagStatus">
                                <c:choose>
                                    <c:when test="${(vacationsPageList.page + 1) == tagStatus.index}">
                                        <li class="page-item disabled"><a class="page-link" href="#">${tagStatus.index}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/viewVacation/${tagStatus.index}" var="url"/>
                                        <li class="page-item"><a class="page-link" href='<c:out value="${url}"/>'>${tagStatus.index}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${vacationsPageList.lastPage}">
                                    <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/viewVacation/next" var="url"/>
                                    <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>Next</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>

    <%@include file="footer.jsp" %>
</html>
