<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome</title>

        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
        <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
    </head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-12">
        <div class="row justify-content-md-center">
            <h3>Welcom To Organizational Management System</h3>
        </div>
        <div class="row justify-content-md-center">
            <div class="panel-body">
                <ul class="nav <%--justify-content-center--%>">
                    <li class="nav-item">
                        <a class="nav-link active" href="<%=request.getContextPath()%>/employee">Add Employee</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/email">Send Email</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/vacations">Request Vacation</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/category">Ipmort Category</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/categoryElement">Import Category Element</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>
