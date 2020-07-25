<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<%
   String categoryId = request.getParameter("categoryId");
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Category</title>

        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet" />
        <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-offset-2 col-md-12">
                <div class="row justify-content-md-center">
                    <h3>Update Category</h3>
                </div>
                <div class="row justify-content-md-center">
                    <div class="panel-body">
                        <ul class="nav">
                            <li class="nav-item">
                                <a class="nav-link disabled" href="<%=request.getContextPath()%>/">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="<%=request.getContextPath()%>/employee">Add Employee</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="<%=request.getContextPath()%>/email">Send Email</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="<%=request.getContextPath()%>/vacations">Request Vacation</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="<%=request.getContextPath()%>/category">Ipmort Category</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="<%=request.getContextPath()%>/categoryElement">Import Category Element</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <form method="POST" action="<%=request.getContextPath()%>/saveCategory">
                        <fieldset class="form-border">
                            <legend class="form-border">Category:</legend>
                            <div class="form-group">
                                <label for="InputCategoryName">Category Name</label>
                                <%--<input type="hidden" name="id" value="<%= categoryId == null ? 0 : Long.parseLong(categoryId)%>"/>--%>
                                <input type="text" name="categoryName" class="form-control" id="InputCategoryName" aria-describedby="categoryHelp" placeholder="Category Name"/>
                                <small id="categoryHelp" class="form-text text-muted">Please enter your category name.</small>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
