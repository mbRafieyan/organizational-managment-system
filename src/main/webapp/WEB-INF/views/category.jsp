<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Category</title>

        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet" />
        <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-offset-2 col-md-12">
                <div class="row justify-content-md-center">
                    <h3>Category</h3>
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
                    <a href="<%=request.getContextPath()%>/updateCategory" class="btn btn-primary">add category</a>
                </div>
            </div>
        </div>
    </body>
</html>
