<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" href="<%=request.getContextPath()%>/resources/image/dotin.jpg">

        <title>Welcome</title>

        <!-- Bootstrap core CSS -->
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">

    </head>
    <body>
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <h5 class="my-0 mr-md-auto font-weight-normal">Organizational Managment System</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="<%=request.getContextPath()%>/">Home</a>
                <a class="p-2 text-dark" href="<%=request.getContextPath()%>/employee">Employee</a>
                <a class="p-2 text-dark" href="<%=request.getContextPath()%>/email">Email</a>
                <a class="p-2 text-dark" href="<%=request.getContextPath()%>/vacations">Vacation</a>
                <a class="p-2 text-dark" href="<%=request.getContextPath()%>/category">Category</a>
                <a class="p-2 text-dark" href="<%=request.getContextPath()%>/categoryElement">CategoryElement</a>
            </nav>
            <a class="btn btn-outline-primary" href="#">Sign up</a>
        </div>
        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
            <h1 class="display-4">Organizational Managment System</h1>
            <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It's built with default Bootstrap components and utilities with little customization.</p>
        </div>

        <div class="container">
            <div class="card-deck mb-3 text-center">

                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Employee</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/employee" class="btn btn-lg btn-block btn-outline-primary">Add Employee</a>
                    </div>
                </div>

                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Email</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/email" class="btn btn-lg btn-block btn-outline-primary">Send Email</a>
                    </div>
                </div>

                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Vacation</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/vacations" class="btn btn-lg btn-block btn-outline-primary">Request Vacation</a>
                    </div>
                </div>

                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Category</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/category" class="btn btn-lg btn-block btn-outline-primary">Add Category</a>
                    </div>
                </div>

                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">CategoryElement</h4>
                    </div>
                    <div class="card-body">
                        <a href="<%=request.getContextPath()%>/categoryElement" class="btn btn-lg btn-block btn-outline-primary">Add CategoryElement</a>
                    </div>
                </div>
            </div>

            <footer class="pt-4 my-md-5 pt-md-5 border-top">
                <div class="row">
                    <div class="col-12 col-md">
                        <img class="mb-2" src="<%=request.getContextPath()%>/resources/image/dotin.jpg" alt="" width="24" height="24">
                        <small class="d-block mb-3 text-muted">&copy; 2019-2020</small>
                    </div>

                    <div class="col-6 col-md">
                        <h5>Employee</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="<%=request.getContextPath()%>/employee">Add Employee</a></li>
                        </ul>
                    </div>

                    <div class="col-6 col-md">
                        <h5>Email</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="<%=request.getContextPath()%>/email">Add Email</a></li>
                        </ul>
                    </div>

                    <div class="col-6 col-md">
                        <h5>Vacations</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="<%=request.getContextPath()%>/vacations">Add Vacation</a></li>
                        </ul>
                    </div>

                    <div class="col-6 col-md">
                        <h5>Category</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="<%=request.getContextPath()%>/category">Add Category</a></li>
                        </ul>
                    </div>

                    <div class="col-6 col-md">
                        <h5>CategoryElement</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="<%=request.getContextPath()%>/categoryElement">Add CategoryElement</a></li>
                        </ul>
                    </div>

                </div>
            </footer>
        </div>


        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>

        <script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/holder.min.js"></script>
        <script>
            Holder.addTheme('thumb', {
                bg: '#55595c',
                fg: '#eceeef',
                text: 'Thumbnail'
            });
        </script>
    </body>
</html>
