<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">

        <link rel="stylesheet" href="assets/css/main.css">
        <jsp:include page="base.jsp"></jsp:include>
            <title>Search Results</title>
            <script>
    
            </script>
        </head>
        <body>
        <%
            if (session.getAttribute("currentuser") == null || session.getAttribute("currentuser").equals("")) {
                %><jsp:forward page="index.jsp" /><%
            }

        %>
        <section class="login-block">
            <div class="container">
                <div class="row">
                    <div class="col login-sec">
                        
                        <h2 class="text-center">Search Results  </h2>
                        <form id="login-form" class="form-group" method="POST" autocomplete="off" action="Controller?op=savedata">

                            <div class="form-group" >
                                <label for="InputEmail1" class="text-uppercase">URL</label>
                                <textarea class="form-control" name="result" id="result" placeholder="Enter URL" rows="18" >${resultdata}</textarea>
                                
                            </div>
                            <br/>

                            <div class="col col-md-5">
                                <!--<button type="submit" name="search" class="btn btn-login float-left">Search Again</button>-->
                                <button name="submit" id="save" class="btn btn-login float-right">Save Content</button>
                            </div>

                        </form>

                    </div>

                </div>
        </section>
    </body>
    <%   
        if (request.getParameter("search") != null) {
            String op = request.getParameter("op");
            if (op != null && op.equals("searchagain")) {

                response.sendRedirect("urlentry.jsp");

            }
        }
    %>
</html>
