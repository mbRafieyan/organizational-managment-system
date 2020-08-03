<footer class="pt-4 my-md-5 pt-md-5 border-top">
    <div class="row">
        <div class="col-12 col-md">
            <img class="mb-2" src="<%=request.getContextPath()%>/resources/image/dotin.jpg" alt="" width="24"
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
                <li><a class="text-muted" href="<%=request.getContextPath()%>/viewVacation">Request Vacation</a></li>
            </ul>
        </div>

    </div>
</footer>
</div>


<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
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