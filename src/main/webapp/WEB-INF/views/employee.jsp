<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Employee</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/image/dotin.jpg">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>

<c:if test="${successMassage != null}">
    <div class="alert alert-success text-center" role="alert">${successMassage}</div>
</c:if>

<c:if test="${warningDelete != null}">
    <div class="alert alert alert-danger text-center" role="alert">${warningDelete}</div>
</c:if>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Add, Delete Or Update Your Employee</h1>
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
                <a href="<%=request.getContextPath()%>/viewEmployee/viewAddEmployee"
                   class="btn btn-lg btn-block btn-outline-primary">Add Employee</a>
            </div>
        </div>
    </div>

    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Show Employees</h4>
            </div>

            <c:set value="${employeeEntityList}" var="employeePageList"/>
            <table class="table table-hover" align="center">

                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">firstName</th>
                    <th scope="col">lastName</th>
                    <th scope="col">manager</th>
                    <th scope="col">role</th>
                    <th scope="col">email</th>
                    <th scope="col">action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employeePageList.pageList}" var="employeeEntity" begin="0" step="1"
                           varStatus="rowNumber">
                    <tr>
                        <td align="center">${rowNumber.index+1}</td>
                        <td align="center">${employeeEntity.firstName}</td>
                        <td align="center">${employeeEntity.lastName}</td>
                        <td align="center">${employeeEntity.employeeManager.firstName}${" "}${employeeEntity.employeeManager.lastName}</td>
                        <td align="center">${employeeEntity.employeeRole.code}</td>
                        <td align="center">${employeeEntity.emailAddress}</td>
                        <td align="center">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Action
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">

                                    <c:url value="/viewEmployee/updateEmployee/${employeeEntity.id}" var="updateUrl"/>
                                    <a href='<c:out value="${updateUrl}" />' class="dropdown-item"
                                       type="button">Update</a>

                                    <c:url value="/viewEmployee/deleteEmployee/${employeeEntity.id}" var="deleteUrl"/>
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
                        <c:when test="${employeePageList.firstPage}">
                            <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/viewEmployee/prev" var="url"/>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>Previous</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${employeePageList.pageCount}" step="1" varStatus="tagStatus">
                        <c:choose>
                            <c:when test="${(employeePageList.page + 1) == tagStatus.index}">
                                <li class="page-item disabled"><a class="page-link" href="#">${tagStatus.index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/viewEmployee/${tagStatus.index}" var="url"/>
                                <li class="page-item"><a class="page-link"
                                                         href='<c:out value="${url}" />'>${tagStatus.index}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${employeePageList.lastPage}">
                            <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/viewEmployee/next" var="url"/>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>Next</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</html>
