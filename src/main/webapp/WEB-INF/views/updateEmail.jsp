<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Send Email</title>
    <link href="<%=request.getContextPath()%>/resources/image/dotin.jpg" rel="icon"/>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/select2.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet"/>
<body>

<%@include file="header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">${subject} Email</h1>
    <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It's built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">
    <div class="card-deck mb-3">
        <div class="card mb-4 shadow-sm">
            <div class="card-header text-center">
                <h4 class="my-0 font-weight-normal">${subject}  Email</h4>
            </div>
            <div class="card-body">
                <c:url var="sendEmail" value="/email/sendEmail" ></c:url>
                <form:form method="POST" action="${sendEmail}" modelAttribute="emailEntity" enctype="multipart/form-data">
                    <div class="form-group">

                        <form:input type="hidden" path="id" />

                        <input type="hidden" name="selectedIds" id="selectedIds"/>

                        <label for="selectEmployee">Employee</label>
                        <select name = "selectedEmployee" id="selectEmployee" class="browser-default custom-select" multiple="multiple">
                            <c:if test = "${not empty emailEntity.recievers}">
                                <c:forEach items="${emailEntity.recievers}" var="employee">
                                    <option value="${employee.id}" selected="selected">${employee.firstName}${" "}${employee.lastName}</option>
                                </c:forEach>
                            </c:if>
                        </select>

                        <label for="inputSubject">Subject</label>
                        <form:input type="text" path="subject" class="form-control" id="inputSubject" placeholder="Subject"/>

                        <label for="editor">Content</label>
                        <form:textarea path="text" cols="80" rows="10" class="form-control" id="editor"></form:textarea>

                        <label for="file">Upload File</label>
                        <input name="file" type="file" class="form-control" id="file"/>

                        <c:if test = "${not empty emailEntity.attachment}">
                            <c:url var="urlFile" value="/email/fileRetriever">
                                <c:param name="emailId" value="${emailEntity.id}"/>
                            </c:url>
                            <a href="${urlFile}"><c:out value="attachmentFile"/></a>
                        </c:if>
                    </div>

                    <c:url value='${not empty emailEntity.senderEmployee.id ? "/email" : "/email/viewEmail" }' var="backUrl">
                        <c:param name="mailBoxName" value="sent"/>
                        <c:param name="employeeId" value="${emailEntity.senderEmployee.id}"/>
                    </c:url>
                    <a type="button" href="${backUrl}" class="btn btn-dark">Cancel</a>

                    <c:if test = "${empty emailEntity.recievers}">
                        <form:button type="submit" class="btn btn-primary">Send</form:button>
                    </c:if>

                </form:form>
            </div>
        </div>
    </div>

    <c:url var="searchEmployeeURL" value="/email/searchEmployee" ></c:url>

    <%@include file="footer.jsp" %>
    <script src="<%= request.getContextPath() %>/resources/js/select2.full.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {

            $('#selectEmployee').select2({
                ajax: {
                    url: '${searchEmployeeURL}',
                    dataType: 'json',
                    data: function (params) {
                        return {
                            'param': params.term, // search term
                        };
                    },
                    processResults: function (data) {
                        return {
                            results: $.map(data, function(obj) {
                                return{
                                    id: obj.employeeId,
                                    text: obj.employeeName,
                                };
                            })
                        };
                    },
                    cache: true
                },
                multiple:true,
                placeholder: "please enter Employee",
                minimumInputLength: 2,
                templateResult: formatRepo,
                templateSelection: formatRepoSelection,

            });

            function formatRepo (repo) {
                if (repo.loading) {
                    return repo.text;
                }

                return repo.text;
            }

            function formatRepoSelection (repo) {
                return  repo.text;
            }

            $(".btn-primary").on('click', function () {
                var values = $('#selectEmployee').val();
                $("#selectedIds").val(values);
            })


        });

    </script>
</html>
