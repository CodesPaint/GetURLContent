<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">

        <link rel="stylesheet" href="assets/css/main.css">
        <jsp:include page="base.jsp"></jsp:include>
        <title>URL Entry</title>

        
    </head>
    <body>
        <%
            if (session.getAttribute("currentuser") == null || session.getAttribute("currentuser").equals(""))
                %><jsp:forward page="index.jsp" /><%
                
            %>
        <section class="login-block">
            <div class="container">

                <div class="row">
                    <div class="col-md-4 login-sec">
                        <h2 class="text-center">URL  </h2>
                        <form id="login-form" class="form-group" method="POST" autocomplete="off" action="Controller?op=search">

                            <div class="form-group" >
                                <label for="InputURL" class="text-uppercase">URL</label>
                                <input type="text" name="txturl" class="form-control" placeholder="Enter URL" required>

                            </div>
                            <br/>

                            <div>
                                <button type="submit" name="search" class="btn btn-login float-right">Search</button>
                            </div>

                        </form>

                    </div>
                    <div class="col-md-8 banner-sec"></div>
                </div>
        </section>
    </body>
                
</html>
