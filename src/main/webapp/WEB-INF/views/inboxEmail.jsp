<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>inboxEmail</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/images/dotin.jpg">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp" %>

<c:if test="${successMassage != null}">
<div class="alert alert-success text-center" role="alert">${successMassage}</div>
</c:if>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Show And Delete Inbox Emails</h1>
    <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example.
        It's built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">

    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Inbox Emails</h4>
            </div>

            <c:set value="${emailEntities}" var="emailPageList"/>
            <table class="table table-hover" align="center">

                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">from</th>
                    <th scope="col">subject</th>
                    <th scope="col">action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${emailPageList.pageList}" var="emailEntity" begin="0" step="1"
                           varStatus="rowNumber">
                    <tr>
                        <td align="center">${rowNumber.index+1}</td>
                        <td align="center">
                            <c:out value="${emailEntity.senderEmployee.firstName}${\" \"}${emailEntity.senderEmployee.lastName}" />
                        </td>
                        <td align="center">${emailEntity.subject}</td>
                        <td align="center">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Action
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">

                                    <c:url value="/email/viewAddEmail" var="showUrl">
                                        <c:param name="emailId" value="${emailEntity.id}"/>
                                        <c:param name="mailBoxName" value='<%=request.getParameter("mailBoxName")%>'/>
                                    </c:url>
                                    <a href='<c:out value="${showUrl}" />' class="dropdown-item"
                                       type="button">show</a>

                                    <c:url value="/email/deleteEmail/${emailEntity.id}" var="deleteUrl"/>
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
                        <c:when test="${emailPageList.firstPage}">
                            <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/email/prev" var="url">
                                <c:param name="employeeId" value='${employeeId ? employeeId : "0"}'/>
                                <c:param name="mailBoxName" value="inboxEmail"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>Previous</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${emailPageList.pageCount}" step="1" varStatus="tagStatus">
                        <c:choose>
                            <c:when test="${(emailPageList.page + 1) == tagStatus.index}">
                                <li class="page-item disabled"><a class="page-link" href="#">${tagStatus.index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/email/${tagStatus.index}" var="url">
                                    <c:param name="employeeId" value='${employeeId ? employeeId : "0"}'/>
                                    <c:param name="mailBoxName" value="inboxEmail"/>
                                </c:url>
                                <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${tagStatus.index}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${emailPageList.lastPage}">
                            <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/email/next" var="url">
                                <c:param name="employeeId" value='${employeeId ? employeeId : "0"}'/>
                                <c:param name="mailBoxName" value="inboxEmail"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>Next</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</html>
